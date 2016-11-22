package com.jbfg.api;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sungpil Hyun on 2016. 11. 3..
 *
 * API 요청시 body 가 필요한 API 중 일부는 Class로 제공됩니다.
 * 제공 되는 클래스는 API 밑에 Class 명을 표시하였습니다.
 *
 * 항목이 하나 인 경우에는 Map을 사용하면 됩니다.
 *
 * Location -> Map 표현은 Location object 를 Map 에 넣어서 사용하라는 의미입니다.
 *
 *
 */
public class API {

    public static String host = "https://jbfg.openbankproject.com";
    public static String version = "v2.1.0";
    public static String prefix = "/obp/" + version;

    /**
     * apply parameters to API
     * URI 의 path variable 을 입력되는 파라미터 순서대로 채워줍니다.
     * 필요한 path variable 과 입력되는 파라미터의 수가 일치해야 합니다.
     *
     * @param apiString
     * @param parameter
     * @return
     * @throws Exception -> 필요한 parameter와 입력된 파라미터 수가 다른 경우 에러 발생함
     */
    public static String applyAPIParameter(String apiString, String... parameter) throws Exception {

        //TODO: change to REGEX
        char[] api = apiString.toCharArray();
        List<String> replacement = new ArrayList<>();

        for (int i = 0; i < api.length; i++) {
            if (api[i] == '{') {
                StringBuffer cache = new StringBuffer();
                for (int j = i; j < api.length; j++) {
                    cache.append(api[j]);
                    if (api[j] == '}')
                        break;
                }
                replacement.add(cache.toString());
            }
        }

        if (replacement.size() != parameter.length) {
            throw new Exception("not enough parameters");
        }

        for (int i = 0; i < replacement.size(); i++) {
            apiString = apiString.replace(replacement.get(i), parameter[i]);
        }

        return  prefix + apiString;
    }

    public static String CreateAccount = "/banks/{bank_id}/accounts/{new_account_id}";  //PUT
    //Account
    //{  "user_id":"A user_id",  "label":"CURRENT",  "type":"Label",  "balance":{    "currency":"EUR",    "amount":"0"  }}

    public static String CreateView = "/banks/{bank_id}/accounts/{account_id}/views";  //POST
    //View
    // {  "name":"Name of view to create",  "description":"Description of view (this example is public, uses the public alias, and has limited access to account data)",  "is_public":true,  "which_alias_to_use":"_public_",  "hide_metadata_if_alias_used":true,  "allowed_actions":["can_see_transaction_start_date","can_see_bank_account_label","can_see_tags"]}

    public static String DeleteView = "/banks/{bank_id}/accounts/{account_id}/views/{view_id}"; //DELETE

    public static String GetAccountByIdCore = "/my/banks/{bank_id}/accounts/{account_id}/account"; //GET

    public static String GetAccountByIdFull = "/banks/{bank_id}/accounts/{account_id}/view_id/account"; //GET

    public static String GetPrivateAccountsAtBank = "/my/banks/{bank_id}/accounts"; //GET

    public static String GetPublicAccountsAtBank = "/banks/{bank_id}/accounts/public";  //GET

    public static String GetPrivateAccountsAtAllPBanks = "/my/accounts";  //GET

    public static String GetPublicAndPrivateAccountsAtOneBank = "/banks/{bank_id}/accounts"; //GET

    public static String GetCounterpartyById = "/banks/{bank_id}/accounts/{account_id}/{view_id}/other_accounts/{other_account_id}"; //GET

    public static String GetPublicAccountsAtAllBanks = "/accounts/public"; //GET

    public static String GetTransactionById = "/banks/{bank_id}/accounts/{account_id}/{view_id}/transactions/{transaction_id}/transaction"; //GET

    public static String GetTransactionsForAccountCore = "/my/banks/{bank_id}/accounts/{account_id}/transactions"; //GET

    public static String GetTransactionsForAccountFull = "/banks/{bank_id}/accounts/{account_id}/{view_id}/transactions"; //GET

    public static String GetViewsForAccount = "/banks/{bank_id}/accounts/{account_id}/views"; //GET

    public static String GetAccountsAtAllBanks = "/accounts"; //GET

    public static String UpdateAccountLabel = "/banks/{bank_id}/accounts/{account_id}"; //POST
    //AccountLabel
    //{  "id":"{account_id} of the account we want to update",  "label":"New label",  "bank_id":"{bank_id}"}

    public static String UpdateView = "/banks/{bank_id}/accounts/{account_id}/views/{view_id}"; //PUT
    //View
    //{  "description":"New description of view",  "is_public":false,  "which_alias_to_use":"_public_",  "hide_metadata_if_alias_used":true,  "allowed_actions":["can_see_transaction_start_date","can_see_bank_account_label"]}

    public static String GetBank = "/banks/{bank_id}"; //GET

    public static String GetBankATMS = "/banks/{bank_id}/atms"; //GET

    public static String GetBankBranches = "/banks/{bank_id}/branches"; //GET

    public static String GetBankProducts = "/banks/{bank_id}/products"; //GET

    public static String GetBanks = "/banks"; //GET

    public static String GetTransactionTypesOfferedByTheBank = "/banks/{bank_id}/transaction-types"; //GET

    public static String GetTheTransactionRequestTypesSupportedByTheBank = "/banks/{bank_id}/transaction-request-types"; //GET

    public static String AddKYCCheck = "/banks/{bank_id}/customers/{customer_id}/kyc_check/{kyc_check_id}"; //PUT
    //KYCCheck
    //{  "customer_number":"1239879",  "date":"2013-01-22T00:08:00Z",  "how":"online_meeting",  "staff_user_id":"67876",  "staff_name":"Simon Redfern",  "satisfied":true,  "comments":""}

    public static String AddKYCDocument = "/banks/{bank_id}/customers/{customer_id}/kyc_documents/{kyc_document_id}"; //PUT
    //KYCDocument
    //{  "customer_number":"1234",  "type":"passport",  "number":"123567",  "issue_date":"2013-01-22T00:08:00Z",  "issue_place":"London",  "expiry_date":"2013-01-22T00:08:00Z"}
    public static String AddKYCMedia = "/banks/{bank_id}/customers/{customer_id}/kyc_media/{kyc_media_id}"; //PUT
    //KYCMedia
    //{  "customer_number":"1239879",  "type":"image",  "url":"http://www.example.com/id-docs/123/image.png",  "date":"2013-01-22T00:08:00Z",  "relates_to_kyc_document_id":"wuwjfuha234678",  "relates_to_kyc_check_id":"98FRd987auhf87jab"}

    public static String AddKYCStatus = "/banks/{bank_id}/customers/{customer_id}/kyc_statuses"; //PUT
    //KYCStatus
    //{  "customer_number":"8762893876",  "ok":true,  "date":"2013-01-22T00:08:00Z"}

    public static String AddSocialMediaHandle = "/banks/{bank_id}/customers/{customer_id}/social_media_handles"; //POST
    //SocialMediaHandle
    //{  "customer_number":"8762893876",  "type":"twitter",  "handle":"susan@example.com",  "date_added":"2013-01-22T00:08:00Z",  "date_activated":"2013-01-22T00:08:00Z"}

    public static String GetCRMEventsForTheLoggedInUser = "/banks/{bank_id}/crm-events"; //GET

    public static String GetKYCChecksForCurrentCustomer = "/customers/{customer_id}/kyc_checks"; //GET

    public static String GetKYCDocumentsForCustomer = "/customers/{customer_id}/kyc_documents"; //GET

    public static String GetKYCMediaForCustomer = "/customers/{customer_id}/kyc_media"; //GET

    public static String GetCardsForTheCurrentUser = "/cards"; //GET

    public static String GetCardsForTheSpecifiedBank = "/banks/{bank_id}/cards"; //GET

    public static String GetCustomerForLoggedInUser = "/banks/{bank_id}/customer"; //GET

    public static String GetSociaMediaHandlesForCustomer = "/banks/{bank_id}/customers/{customer_id}/social_media_handles"; //GET

    public static String GetKYCStatusesForCustomer = "/customers/{customer_id}/kyc_statuses"; //GET

    public static String CreateMeeting = "/banks/{bank_id}/meetings"; //POST
    //{  "provider_id":"tokbox",  "purpose_id":"onboarding"}

    public static String GetMeeting = "/banks/{bank_id}/meetings/{meeting_id}"; //GET

    public static String GetMeetings = "/banks/{bank_id}/meetings"; //GET

    public static String AddCorporateLocationToCounterparty = "/banks/{bank_id}/accounts/{account_id}/{view_id}/other_accounts/{other_account_id}/metadata/corporate_location"; //POST
    //Location  -> Map
    //{  "corporate_location":{    "latitude":52.5571573,    "longitude":13.3728025  }}

    public static String AddCounterpartymoreInfo = "/banks/{bank_id}/accounts/{account_id}/{view_id}/other_accounts/{other_account_id}/metadata/more_info"; //POST
    //{  "more_info":"More info"}

    public static String AddOpenCorporatesURLtoCounterparty = "/banks/{bank_id}/accounts/{account_id}/{view_id}/other_accounts/{other_account_id}/metadata/open_corporates_url"; //POST
    //{  "open_corporates_URL":"https://opencorporates.com/companies/gb/04351490"}

    public static String AddTag = "/banks/{bank_id}/accounts/{account_id}/{view_id}/transactions/{transaction_id}/metadata/tags"; //POST
    //{  "value":"holiday"}

    public static String AddImage = "/banks/{bank_id}/accounts/{account_id}/{view_id}/transactions/{transaction_id}/metadata/images"; //POST
    //Image
    //{  "label":"The new printer",  "URL":"www.example.com/images/printer.png"}

    public static String AddComment = "/banks/{bank_id}/accounts/{account_id}/{view_id}/transactions/{transaction_id}/metadata/comments"; //POST
    //{  "value":"Why did we spend money on this again?"}

    public static String AddImageUrlToOtherBankAccount = "/banks/{bank_id}/accounts/{account_id}/{view_id}/other_accounts/{other_account_id}/metadata/image_url"; //POST
    //{  "image_URL":"www.example.com/logo.png"}

    public static String AddNarrative = "/banks/{bank_id}/accounts/{account_id}/{view_id}/transactions/{transaction_id}/metadata/narrative"; //POST
    //{  "narrative":"My new (old!) piano"}

    public static String AddPhysicalLocationToOtherBankAccount = "/banks/{bank_id}/accounts/{account_id}/{view_id}/other_accounts/{other_account_id}/metadata/physical_location"; //POST
    // Location -> Map
    //{  "physical_location":{    "latitude":52.5571573,    "longitude":13.3728025  }}

    public static String AddPublicAliasToOtherBanAccount = "/banks/{bank_id}/accounts/{account_id}/{view_id}/other_accounts/{other_account_id}/public_alias"; //POST
    //{  "alias":"An Alias"}

    public static String AddUrlToOtherBankAccount = "/banks/{bank_id}/accounts/{account_id}/{view_id}/other_accounts/{other_account_id}/metadata/url"; //POST
    //{  "URL":"www.example.com"}

    public static String AddWhereTag = "/banks/{bank_id}/accounts/{account_id}/{view_id}/transactions/{transaction_id}/metadata/where"; //POST
    //Location -> Map
    //{  "where":{    "latitude":52.5571573,    "longitude":13.3728025  }}

    public static String CreateCounterpartyPrivateAlias = "/banks/{bank_id}/accounts/{account_id}/{view_id}/other_accounts/{other_account_id}/public_alias"; //POST
    //{  "alias":"An Alias"}

    public static String DeleteCounterpartyCorporateLocation = "/banks/{bank_id}/accounts/{account_id}/{view_id}/other_accounts/{other_account_id}/metadata/corporate_location";  //DELETE

    public static String DeleteCounterpartyImageURL = "/banks/{bank_id}/accounts/{account_id}/{view_id}/other_accounts/{other_account_id}/metadata/image_url"; //DELETE

    public static String DeleteCounterpartyOpenCorporatesURL = "/banks/{bank_id}/accounts/{account_id}/{view_id}/other_accounts/{other_account_id}/metadata/open_corporates_url"; //DELETE

    public static String DeleteCounterpartyPhysicalLocation = "/banks/{bank_id}/accounts/{account_id}/{view_id}/other_accounts/{other_account_id}/metadata/physical_location"; //DELETE

    public static String DeleteCounterpartyPrivateAlias = "/banks/{bank_id}/accounts/{account_id}/{view_id}/other_accounts/{other_account_id}/public_alias"; //DELETE

    public static String DeleteCounterpartyPublicAlias = "/banks/{bank_id}/accounts/{account_id}/{view_id}/other_accounts/{other_account_id}/public_alias"; //DELETE

    public static String DeleteTag = "/banks/{bank_id}/accounts/{account_id}/{view_id}/transactions/{transaction_id}/metadata/tags/{tag_id}"; //DELETE

    public static String DeleteImage = "/banks/{bank_id}/accounts/{account_id}/{view_id}/transactions/{transaction_id}/metadata/images/{image_id}"; //DELETE

    public static String DeleteComment = "/banks/{bank_id}/accounts/{account_id}/{view_id}/transactions/{transaction_id}/metadata/comments/{comment_id}"; //DELETE

    public static String DeleteMoreInfoOfOtherBankAccount = "/banks/{bank_id}/accounts/{account_id}/{view_id}/other_accounts/{other_account_id}/metadata/more_info"; //DELETE

    public static String DeleteNarrative = "/banks/{bank_id}/accounts/{account_id}/{view_id}/transactions/{transaction_id}/metadata/narrative"; //DELETE

    public static String DeleteUrlOfOtherBankAccount = "/banks/{bank_id}/accounts/{account_id}/{view_id}/other_accounts/{other_account_id}/metadata/url"; //DELETE

    public static String DeleteWhereTag = "/banks/{bank_id}/accounts/{account_id}/{view_id}/transactions/{transaction_id}/metadata/where"; //DELETE

    public static String GetCounterpartyMetadata = "/banks/{bank_id}/accounts/{account_id}/{view_id}/other_accounts/{other_account_id}/metadata"; //GET

    public static String GetCounterpartyPrivateAlias = "/banks/{bank_id}/accounts/{account_id}/{view_id}/other_accounts/{other_account_id}/public_alias"; //GET

    public static String GetComments = "/banks/{bank_id}/accounts/{account_id}/{view_id}/transactions/{transaction_id}/metadata/comments"; //GET

    public static String GetImages = "/banks/{bank_id}/accounts/{account_id}/{view_id}/transactions/{transaction_id}/metadata/images"; //GET

    public static String GetNarrative = "/banks/{bank_id}/accounts/{account_id}/{view_id}/transactions/{transaction_id}/metadata/narrative"; //GET

    public static String GetPublicAliasOfOtherBankAccount = "/banks/{bank_id}/accounts/{account_id}/{view_id}/other_accounts/{other_account_id}/public_alias"; //GET

    public static String GetTags = "/banks/{bank_id}/accounts/{account_id}/{view_id}/transactions/{transaction_id}/metadata/tags"; //GET

    public static String GetWhereTag = "/banks/{bank_id}/accounts/{account_id}/{view_id}/transactions/{transaction_id}/metadata/where"; //GET

    public static String UpdateCounterpartyCorporateLocation = "/banks/{bank_id}/accounts/{account_id}/{view_id}/other_accounts/{other_account_id}/metadata/corporate_location"; //PUT
    //Location -> Map
    //{  "corporate_location":{    "latitude":52.5571573,    "longitude":13.3728025  }}

    public static String UpdateCounterpartyImageUrl = "/banks/{bank_id}/accounts/{account_id}/{view_id}/other_accounts/{other_account_id}/metadata/image_url"; //PUT
    //{  "image_URL":"www.example.com/logo.png"}

    public static String UpdateCounterpartyMoreInfo = "/banks/{bank_id}/accounts/{account_id}/{view_id}/other_accounts/{other_account_id}/metadata/more_info"; //PUT
    //{  "more_info":"More info"}

    public static String UpdateCounterpartyPhysicalLocation = "/banks/{bank_id}/accounts/{account_id}/{view_id}/other_accounts/{other_account_id}/metadata/physical_location"; //PUT
    //Location -> Map
    //{  "physical_location":{    "latitude":52.5571573,    "longitude":13.3728025  }}

    public static String UpdateCounterpartyPrivateAlias = "/banks/{bank_id}/accounts/{account_id}/{view_id}/other_accounts/{other_account_id}/public_alias"; //PUT
    //{  "alias":"An Alias"}

    public static String UpdateOpenCorporatesUrlofCounterparty = "/banks/{bank_id}/accounts/{account_id}/{view_id}/other_accounts/{other_account_id}/metadata/open_corporates_url"; //PUT
    //{  "open_corporates_URL":"https://opencorporates.com/companies/gb/04351490"}

    public static String UpdateNarrative = "/banks/{bank_id}/accounts/{account_id}/{view_id}/transactions/{transaction_id}/metadata/narrative";//PUT
    //{  "narrative":"My new (old!) piano"}

    public static String UpdatePublicAliasOfOtherBankAccount = "/banks/{bank_id}/accounts/{account_id}/{view_id}/other_accounts/{other_account_id}/public_alias"; //PUT
    //{  "alias":"An Alias"}

    public static String UpdateUrlOfOtherBankAccount = "/banks/{bank_id}/accounts/{account_id}/{view_id}/other_accounts/{other_account_id}/metadata/url"; //PUT
    //{  "URL":"www.example.com"}

    public static String UpdateWhereTag = "/banks/{bank_id}/accounts/{account_id}/{view_id}/transactions/{transaction_id}/metadata/where"; //PUT
    //Location -> Map
    //{  "where":{    "latitude":52.5571573,    "longitude":13.3728025  }}

    public static String CreateUser = "/users"; //POST
    //{  "email":"someone@example.com",  "username":"my-username",  "password":"my-secure-password",  "first_name":"James",  "last_name":"Brown"}

    public static String AddCustomerMessage = "/banks/{bank_id}/customer/{customer_id}/messages"; //POST
    //CustomerMessage
    //{  "message":"message to send",  "from_department":"from department",  "from_person":"from person"}

    public static String CreateCustomer = "/banks/{bank_id}/customers"; //POST
    //Customer
    //{  "user_id":"user_id to attach this customer to e.g. 123213",  "customer_number":"new customer number 687687678",  "legal_name":"Joe David Bloggs",  "mobile_phone_number":"+44 07972 444 876",  "email":"person@example.com",  "face_image":{    "url":"www.example.com/person/123/image.png",    "date":"2013-01-22T00:08:00Z"  },  "date_of_birth":"2013-01-22T00:08:00Z",  "relationship_status":"Single",  "dependants":1,  "dob_of_dependants":["2013-01-22T00:08:00Z"],  "highest_education_attained":"Bachelor’s Degree",  "employment_status":"Employed",  "kyc_status":true,  "last_ok_date":"2013-01-22T00:08:00Z"}

    public static String CreateUserCustomerLink = "/banks/user_customer_links"; //POST
    //UserCustomerLink
    //{  "user_id":"be106783-b4fa-48e6-b102-b178a11a8e9b",  "customer_id":"02141bc6-0a69-4fba-b4db-a17e5fbbbdcc"}

    public static String GetCounterpartiesOfOneAccount = "/banks/{bank_id}/accounts/{account_id}/{view_id}/other_accounts"; //GET

    public static String GetCustomerMessagesCurrent = "/banks/{bank_id}/customer/messages"; //GET

    public static String GetUserCurrent = "/users/current"; //GET

    public static String GetUsersByEmailAddress = "/users/{user_email}"; //GET

    public static String GetAccessForSpecificUser = "/banks/{bank_id}/accounts/{account_id}/permissions/{provider_id}/{user_id}"; //GET

    public static String GetAccess = "/banks/{bank_id}/accounts/{account_id}/permissions"; //GET

    public static String GetAllCustomersForLoggedInUser = "/users/current/customers"; //GET

    public static String GrantUserAccessToView = "/banks/{bank_id}/accounts/{account_id}/permissions/{provider_id}/{user_id}/views/{view_id}"; //POST
    // { }

    public static String GrantUserAccessToListOfViews = "/banks/{bank_id}/accounts/{account_id}/permissions/{provider_id}/{user_id}/views"; //POST
    //{  "views":["owner","auditor","investor"]}

    public static String RevokeAccessToAllViewOnAccount = "/banks/{bank_id}/accounts/{account_id}/permissions/{provider_id}/{user_id}/views"; //DELETE

    public static String RevokeAccessToOneView = "/banks/{bank_id}/accounts/{account_id}/permissions/{provider_id}/{user_id}/views/{view_id}"; //DELETE

    public static String GetCounterpartyOfTransaction = "/banks/{bank_id}/accounts/{account_id}/{view_id}/transactions/{transaction_id}/other_account";  //GET

    public static String AnswerTransactionRequestChallenge = "/banks/{bank_id}/accounts/{account_id}/{view_id}/transaction-request-types/{transaction_request_type}/transaction-requests/{transaction_request_id}/challenge"; //POST
    //{  "id":"89123812",  "answer":"123345"}

    public static String CreateTransactionRequest = "/banks/{bank_id}/accounts/{account_id}/{view_id}/transaction-request-types/{transaction_request_type}/transaction-requests"; //POST
    //TransactionRequest
    //{  "to":{    "bank_id":"BANK_ID",    "account_id":"ACCOUNT_ID"  },  "value":{    "currency":"EUR",    "amount":"100.53"  },  "description":"A description for the transaction to be created"}

    public static String GetTransactionRequestTypesFoAccount = "/banks/{bank_id}/accounts/{account_id}/{view_id}/transaction-request-types"; //GET

    public static String GetTransactionRequests = "/banks/{bank_id}/accounts/{account_id}/{view_id}/transaction-requests"; //GET

    public static String MakePayment = "/banks/{bank_id}/accounts/{account_id}/{view_id}/transactions"; //POST
    //Payment
    //{  "bank_id":"To BANK_ID",  "account_id":"To ACCOUNT_ID",  "amount":"12.45"}

    public static String AddEntitlementForUser = "/users/{user_id}/entitlements";  //POST
    //Entitlement
    //{  "bank_id":"obp-bank-x-gh",  "role_name":"CanQueryOtherUser"}

    public static String DeleteEntitlement = "/users/{user_id}/entitlement/{entitlement_id}"; //DELETE

    public static String GetEntitlementsSpecifiedByBankAndUser = "/banks/{bank_id}/users/{user_id}/entitlements"; //GET

    public static String GetEntitlementsSpecifiedByUser = "/users/{user_id}/entitlements"; //GET

    public static String GetRoles = "/roles"; //GET

    public static String GetAllEntitlements = "/entitlements"; //GET


}
