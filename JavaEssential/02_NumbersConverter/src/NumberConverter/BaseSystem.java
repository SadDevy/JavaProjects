package NumberConverter;

public enum BaseSystem {
    Bin(2),
    Oct(8),
    Hex(16),
    Base32(32),
    Base64(64);

    private int code;
    public int getCode() {
        return code;
    }

    BaseSystem(int code) {
        this.code = code;
    }
}
