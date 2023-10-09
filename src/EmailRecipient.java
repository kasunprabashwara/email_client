public abstract class EmailRecipient {
    protected String name,address;     
    protected EmailRecipient(String name,String address){
        this.name=name;
        this.address=address;
    }
    public void printDetails(){}
}