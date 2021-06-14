package me.tim.plugin.checks.result;

public class CheckResult {
    private Level lvl;
    private String msg;

    public CheckResult(Level lvl, String msg) {
        this.lvl = lvl;
        this.msg = msg;
    }

    public Level getLevel() {
        return lvl;
    }

    public String getMessage() {
        return msg;
    }

    public boolean failed() {
        return lvl != Level.PASSED;
    }
}
