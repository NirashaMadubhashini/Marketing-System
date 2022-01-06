package view.tm;

public class CustomerTM {
     private String customerId;
     private String CustomerTitle;
     private String customerName;
     private String customerAddress;
     private String city;
     private String province;
     private String postalCode;

    public CustomerTM() {
    }

    public CustomerTM(String customerId, String customerTitle, String customerName, String customerAddress, String city,
                      String province, String postalCode) {
        this.setCustomerId(customerId);
        setCustomerTitle(customerTitle);
        this.setCustomerName(customerName);
        this.setCustomerAddress(customerAddress);
        this.setCity(city);
        this.setProvince(province);
        this.setPostalCode(postalCode);
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerTitle() {
        return CustomerTitle;
    }

    public void setCustomerTitle(String customerTitle) {
        CustomerTitle = customerTitle;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
