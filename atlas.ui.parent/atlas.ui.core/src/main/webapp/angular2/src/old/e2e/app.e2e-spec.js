/*
 * Copyright (C) 2017 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
"use strict";
var protractor_1 = require('protractor');
describe('QuickStart E2E Tests', function () {
    var expectedMsg = 'Hello Angular';
    beforeEach(function () {
        protractor_1.browser.get('');
    });
    it('should display: ' + expectedMsg, function () {
        expect(protractor_1.element(protractor_1.by.css('h1')).getText()).toEqual(expectedMsg);
    });
});
//# sourceMappingURL=app.e2e-spec.js.map