package NumberConverter;

public class Converter {
    private static String toBin(int value) {
        final String digits = "01";
        return toBase(value, digits);
    }

    private static String toOct(int value) {
        final String digits = "01234567";
        return toBase(value, digits);
    }

    private static String toHex(int value) {
        final String digits = "0123456789ABCDEF";
        return toBase(value, digits);
    }

    private static String toBase32(int value) {
        final String digits = "0123456789ABCDEFGHJKMNPQRSTVWXYZ";
        return toBase(value, digits);
    }

    private static String toBase64(int value) {
        final String digits = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmopqrstuvwxyz0123456789+/";
        return toBase(value, digits);
    }

    private static String toBase(int value, String digits) {
        int toBase = digits.length();
        StringBuilder result = new StringBuilder();
        while(value > 0) {
            char digit = digits.charAt(value % toBase);
            result.insert(0, digit);
            value /= toBase;
        }

        return result.toString();
    }

    private static String toBinStandard(int value) {
        return Integer.toString(value, BaseSystem.Bin.getCode());
    }

    private static String toOctStandard(int value) {
        return Integer.toString(value, BaseSystem.Oct.getCode());
    }

    private static String toHexStandard(int value) {
        return Integer.toString(value, BaseSystem.Hex.getCode());
    }

    private static String toBase32Standard(int value) {
        return Integer.toString(value, BaseSystem.Base32.getCode());
    }

    private static String toBase64Standard(int value) {
        return Integer.toString(value, BaseSystem.Base64.getCode());
    }

    public static String toBase(int value, BaseSystem toBase) {
        switch (toBase) {
            case Bin:
                return toBin(value);
            case Oct:
                return toOct(value);
            case Hex:
                return toHex(value);
            case Base32:
                return toBase32(value);
            case Base64:
                return toBase64(value);
            default:
                return null;
        }
    }

    public static String toBaseStandard(int value, BaseSystem toBase) {
        switch (toBase) {
            case Bin:
                return toBinStandard(value);
            case Oct:
                return toOctStandard(value);
            case Hex:
                return toHexStandard(value);
            case Base32:
                return toBase32Standard(value);
            case Base64:
                return toBase64Standard(value);
            default:
                return null;
        }
    }
}
