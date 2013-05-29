class BillingManager {
    static private BillingManager inst = null;
    
    static public BillingManager getInstance() {
        if (inst == null) {
            inst = new BillingManager();
        }
        return inst;
    }
    public void beginCall() {
    }
    public void endCall() {
    }
}
