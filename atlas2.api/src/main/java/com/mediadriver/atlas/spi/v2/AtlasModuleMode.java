package com.mediadriver.atlas.spi.v2;

public enum AtlasModuleMode {
	SOURCE("source"),
	TARGET("target");
	
	private final String mode;
	
	AtlasModuleMode(String mode) {
        this.mode = mode;
    }

    public String value() {
        return this.mode;
    }

    public static AtlasModuleMode fromValue(String v) {
        for (AtlasModuleMode c: AtlasModuleMode.values()) {
            if (c.mode.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
