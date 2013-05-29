import gov.nist.sip.accounting.User;

class BillingManager {
    static private BillingManager inst = null;
    
    static public BillingManager getInstance() {
        if (inst == null) {
            inst = new BillingManager();
        }
        return inst;
    }
    public void beginCall(User from, User to) {
    }
    public void endCall(User from, User to) {
    }
}
