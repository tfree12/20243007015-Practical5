package com.example.a20243007015_practical5;

/**
 * A plain Java utility class that validates and normalises a municipality name.
 * It does not extend Activity and has no associated layout.
 */
public class TextProcessor {

    private TextProcessor() {
        // Utility class: prevent instantiation.
    }

    /**
     * Validates the given municipality name. Returns the correctly capitalised
     * name for one of the four supported municipalities, or {@code null} if the
     * input is null, empty, whitespace-only, or unsupported.
     *
     * @param municipality the raw input to process
     * @return the normalised name, or {@code null} for invalid input
     */
    public static String processMunicipality(String municipality) {
        if (municipality == null || municipality.trim().isEmpty()) {
            return null;
        }

        String name = municipality.trim();

        if (name.equalsIgnoreCase("Beijing")) {
            return "Beijing";
        } else if (name.equalsIgnoreCase("Shanghai")) {
            return "Shanghai";
        } else if (name.equalsIgnoreCase("Tianjin")) {
            return "Tianjin";
        } else if (name.equalsIgnoreCase("Chongqing")) {
            return "Chongqing";
        }

        return null;
    }
}
