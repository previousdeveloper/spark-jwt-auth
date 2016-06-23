package Controller;

public class OauthRequest {
    public String Client_Id;
    public String Client_Secret;
    public String Grant_Type;
    public String Refresh_Token;

    public String getRefresh_Token() {
        return Refresh_Token;
    }

    public void setRefresh_Token(String refresh_Token) {
        Refresh_Token = refresh_Token;
    }

    public String getClient_Id() {
        return Client_Id;
    }

    public void setClient_Id(String client_Id) {
        Client_Id = client_Id;
    }

    public String getClient_Secret() {
        return Client_Secret;
    }

    public void setClient_Secret(String client_Secret) {
        Client_Secret = client_Secret;
    }

    public String getGrant_Type() {
        return Grant_Type;
    }

    public void setGrant_Type(String grant_Type) {
        Grant_Type = grant_Type;
    }
}
