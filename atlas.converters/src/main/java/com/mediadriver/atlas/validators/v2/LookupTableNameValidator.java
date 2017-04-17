/**
 * Copyright (C) 2017 Red Hat, Inc.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mediadriver.atlas.validators.v2;

import com.mediadriver.atlas.v2.LookupTable;
import com.mediadriver.atlas.v2.LookupTables;
import com.mediadriver.atlas.validators.v2.core.AtlasMappingError;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 */
public class LookupTableNameValidator implements Validator {

    private String violationMessage;

    private String field;

    public LookupTableNameValidator(String field, String violationMessage) {
        this.violationMessage = violationMessage;
        this.field = field;
    }

    @Override
    public boolean supports(Class clazz) {
        return LookupTables.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        validate(target, errors, AtlasMappingError.Level.ERROR);
    }

    @Override
    public void validate(Object target, Errors errors, AtlasMappingError.Level level) {
        LookupTables lookupTables = (LookupTables) target;
        List<LookupTable> tables = lookupTables.getLookupTable();
        List<LookupTable> deduped = Collections.unmodifiableList(tables).stream()
                .filter(distinctByKey(LookupTable::getName)).collect(Collectors.toList());
        if (deduped.size() != tables.size()) {
            String dupedName = findDuplicatedName(tables);
            errors.addError(new AtlasMappingError(field, dupedName, violationMessage, level));
        }
    }

    private String findDuplicatedName(List<LookupTable> tables) {
        List<String> names = new ArrayList<>();
        for (LookupTable table : tables) {
            names.add(table.getName());
        }
        Set<String> uniqueSet = new HashSet<>(names);
        for (String s : uniqueSet) {
            if (Collections.frequency(names, s) > 1) {
                return s;
            }
        }
        return null;
    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
