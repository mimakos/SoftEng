import gov.nist.sip.accounting.User;

class AccountingManager {
    public void invite(String uri_a, String uri_b) {
        User.fromURI(uri_a).invite(User.fromURI(uri_b));
    }
    public void cancel(String uri_a, String uri_b) {
        User.fromURI(uri_a).cancel(User.fromURI(uri_b));
    }
    public void ack(String uri_a, String uri_b) {
        User.fromURI(uri_a).ack(User.fromURI(uri_b));
    }
    public void bye(String uri_a, String uri_b) {
        User.fromURI(uri_a).bye(User.fromURI(uri_b));
    }
    public void register(String uri) {
        User.fromURI(uri).register();
    }
}
