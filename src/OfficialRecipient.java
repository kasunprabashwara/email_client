public class OfficialRecipient extends EmailRecipient{
    private String designation;
    public OfficialRecipient(String name,String address,String designation){
        super(name,address);
        this.designation=designation;
    }
    public void printDetails(){
        System.out.println("Name- "+name+"   Designation"+designation);
    }
}