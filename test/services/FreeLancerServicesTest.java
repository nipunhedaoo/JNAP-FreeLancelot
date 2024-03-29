package services;

import controllers.HomeController;
import models.ProjectDetails;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.libs.ws.WSClient;
import play.libs.ws.WSRequest;
import play.libs.ws.WSResponse;
import play.mvc.Result;
import play.test.WithApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static play.test.Helpers.running;

@RunWith(MockitoJUnitRunner.Silent.class)
public class FreeLancerServicesTest extends WithApplication {

@Mock
    FreeLancerServices freeLancerServices;
    @Mock
    WSRequest wsRequest;
    @Mock
    WSClient wsClient;
    @InjectMocks
    HomeController homeController;
    private Class<? extends CompletionStage<WSResponse>> HashMap;


    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }

    /**
     * Test the Words stats individual count
     * @author Alankrit Gupta
     */
    @Test
    public void testWordStatsIndevidual(){
        String answer = "{z=4, Apple=2, banana=1, x=1, y=1}";
        Map<String, Integer> map = freeLancerServices.wordStatsIndevidual("Apple banana x y z Apple z z z");
        assertEquals(map.toString(), answer);
    }

    /**
     * Test the Words stats global count
     * @author Alankrit Gupta
     */
    @Test
    public void testWordStatsGlobal(){
        List <ProjectDetails> testProjectList = new ArrayList<>();
        testProjectList.add(new ProjectDetails(123, 234, null, 12345, "Test title 1", "Test type 1",null, "Apple banana x y z Apple z z z", 0.0, 0.0, "Early"));
        testProjectList.add(new ProjectDetails(345, 234, null, 12345, "Test title 2", "Test type 2",null, "Apple banana x y z Apple z z z", 0.0, 0.0, "Early" ));

        String answer = "{z=8, Apple=4, banana=2, x=2, y=2}";

        Map<String, Integer> map = freeLancerServices.wordStatsGlobal(testProjectList);
        assertEquals(map.toString(), answer);
    }

    /**
     * Test the search skills function for fetching skills
     * @author Jasleen Kaur
     */
    @Test
    public void testSkills() {

        running(provideApplication(), () -> {
            CompletionStage<WSResponse> obj = mock(CompletionStage.class);
            CompletableFuture completableFuture=mock(CompletableFuture.class);
            when(freeLancerServices.searchSkillResults(any())).thenReturn(obj);
            when(obj.toCompletableFuture()).thenReturn(completableFuture);
            CompletionStage<Result> resultCompletionStage=homeController.searchBySkill("9","Java");
       });
    }

    /**
     * Test the search skills function for computing skills
     * @author Jasleen Kaur
     */
    @Test
    public void testIndSkills()
    {
        final String jsonStr="{\"status\":\"success\",\"result\":{\"projects\":[{\"id\":33258357,\"owner_id\":61331320,\"title\":\"Enter data into excel file\",\"status\":\"active\",\"sub_status\":null,\"seo_url\":\"excel/Enter-data-into-excel-file-33258357\",\"currency\":{\"id\":11,\"code\":\"INR\",\"sign\":\"\\u20b9\",\"name\":\"Indian Rupee\",\"exchange_rate\":0.013162,\"country\":\"IN\",\"is_external\":false,\"is_escrowcom_supported\":false},\"description\":null,\"jobs\":[{\"id\":36,\"name\":\"Data Processing\",\"category\":{\"id\":4,\"name\":\"Data Entry & Admin\"},\"active_project_count\":null,\"seo_url\":\"data_processing\",\"seo_info\":null,\"local\":false},{\"id\":39,\"name\":\"Data Entry\",\"category\":{\"id\":4,\"name\":\"Data Entry & Admin\"},\"active_project_count\":null,\"seo_url\":\"data_entry\",\"seo_info\":null,\"local\":false},{\"id\":55,\"name\":\"Excel\",\"category\":{\"id\":4,\"name\":\"Data Entry & Admin\"},\"active_project_count\":null,\"seo_url\":\"excel\",\"seo_info\":null,\"local\":false}],\"submitdate\":1647817441,\"preview_description\":\"Need someone to enter data into an Excel file.\\nFile is already pre-written with main vertical\\ncatego\",\"deleted\":false,\"nonpublic\":false,\"hidebids\":false,\"type\":\"hourly\",\"bidperiod\":7,\"budget\":{\"minimum\":750.0,\"maximum\":1250.0,\"name\":null,\"project_type\":null,\"currency_id\":null},\"hourly_project_info\":{\"commitment\":{\"hours\":40,\"interval\":\"week\"},\"duration_enum\":\"unspecified\"},\"featured\":false,\"urgent\":false,\"assisted\":null,\"active_prepaid_milestone\":null,\"bid_stats\":{\"bid_count\":0,\"bid_avg\":null},\"time_submitted\":1647817441,\"time_updated\":1647817441,\"upgrades\":{\"featured\":false,\"sealed\":false,\"nonpublic\":false,\"fulltime\":false,\"urgent\":false,\"qualified\":false,\"NDA\":false,\"assisted\":null,\"active_prepaid_milestone\":null,\"ip_contract\":false,\"success_bundle\":null,\"non_compete\":false,\"project_management\":false,\"pf_only\":false,\"recruiter\":null,\"listed\":null,\"extend\":null,\"unpaid_recruiter\":null},\"qualifications\":null,\"language\":\"en\",\"attachments\":null,\"hireme\":false,\"hireme_initial_bid\":null,\"invited_freelancers\":null,\"recommended_freelancers\":null,\"frontend_project_status\":\"open\",\"nda_signatures\":null,\"location\":{\"country\":{\"name\":null,\"flag_url\":null,\"code\":null,\"highres_flag_url\":null,\"flag_url_cdn\":null,\"highres_flag_url_cdn\":null,\"iso3\":null,\"region_id\":null,\"phone_code\":null,\"demonym\":null,\"person\":null,\"seo_url\":null,\"sanction\":null,\"language_code\":null,\"language_id\":null},\"city\":null,\"latitude\":null,\"longitude\":null,\"vicinity\":null,\"administrative_area\":null,\"full_address\":null,\"administrative_area_code\":null,\"postal_code\":null},\"true_location\":null,\"local\":false,\"negotiated\":false,\"negotiated_bid\":null,\"time_free_bids_expire\":1647813841,\"can_post_review\":null,\"files\":null,\"user_distance\":null,\"from_user_location\":null,\"project_collaborations\":null,\"support_sessions\":null,\"track_ids\":null,\"drive_files\":null,\"nda_details\":null,\"pool_ids\":[\"freelancer\"],\"enterprise_ids\":[],\"timeframe\":null,\"deloitte_details\":null,\"is_escrow_project\":false,\"is_seller_kyc_required\":false,\"is_buyer_kyc_required\":false,\"local_details\":null,\"equipment\":null,\"nda_signatures_new\":null,\"billing_code\":null,\"enterprise_metadata_values\":null,\"project_reject_reason\":{\"description\":null,\"message\":null},\"repost_id\":null,\"client_engagement\":null,\"contract_signatures\":null,\"quotation_id\":null,\"quotation_version_id\":null,\"enterprise_linked_projects_details\":null,\"equipment_groups\":null,\"project_source\":null,\"project_source_reference\":null},{\"id\":33258282,\"owner_id\":61331191,\"title\":\"Copywriting \",\"status\":\"active\",\"sub_status\":null,\"seo_url\":\"content-writing/Copywriting-33258282\",\"currency\":{\"id\":1,\"code\":\"USD\",\"sign\":\"$\",\"name\":\"US Dollar\",\"exchange_rate\":1.0,\"country\":\"US\",\"is_external\":false,\"is_escrowcom_supported\":true},\"description\":null,\"jobs\":[{\"id\":662,\"name\":\"Content Writing\",\"category\":{\"id\":2,\"name\":\"Writing & Content\"},\"active_project_count\":null,\"seo_url\":\"content_writing\",\"seo_info\":null,\"local\":false}],\"submitdate\":1647817358,\"preview_description\":\"Draft and revise creative, effective copy for tactics that can include direct mail, flyers, videos, \",\"deleted\":false,\"nonpublic\":false,\"hidebids\":false,\"type\":\"fixed\",\"bidperiod\":7,\"budget\":{\"minimum\":250.0,\"maximum\":750.0,\"name\":null,\"project_type\":null,\"currency_id\":null},\"hourly_project_info\":null,\"featured\":false,\"urgent\":false,\"assisted\":null,\"active_prepaid_milestone\":null,\"bid_stats\":{\"bid_count\":4,\"bid_avg\":312.5},\"time_submitted\":1647817358,\"time_updated\":1647817358,\"upgrades\":{\"featured\":false,\"sealed\":false,\"nonpublic\":false,\"fulltime\":false,\"urgent\":false,\"qualified\":false,\"NDA\":false,\"assisted\":null,\"active_prepaid_milestone\":null,\"ip_contract\":false,\"success_bundle\":null,\"non_compete\":false,\"project_management\":false,\"pf_only\":false,\"recruiter\":null,\"listed\":null,\"extend\":null,\"unpaid_recruiter\":null},\"qualifications\":null,\"language\":\"en\",\"attachments\":null,\"hireme\":false,\"hireme_initial_bid\":null,\"invited_freelancers\":null,\"recommended_freelancers\":null,\"frontend_project_status\":\"open\",\"nda_signatures\":null,\"location\":{\"country\":{\"name\":null,\"flag_url\":null,\"code\":null,\"highres_flag_url\":null,\"flag_url_cdn\":null,\"highres_flag_url_cdn\":null,\"iso3\":null,\"region_id\":null,\"phone_code\":null,\"demonym\":null,\"person\":null,\"seo_url\":null,\"sanction\":null,\"language_code\":null,\"language_id\":null},\"city\":null,\"latitude\":null,\"longitude\":null,\"vicinity\":null,\"administrative_area\":null,\"full_address\":null,\"administrative_area_code\":null,\"postal_code\":null},\"true_location\":null,\"local\":false,\"negotiated\":false,\"negotiated_bid\":null,\"time_free_bids_expire\":1647812853,\"can_post_review\":null,\"files\":null,\"user_distance\":null,\"from_user_location\":null,\"project_collaborations\":null,\"support_sessions\":null,\"track_ids\":null,\"drive_files\":null,\"nda_details\":null,\"pool_ids\":[\"freelancer\"],\"enterprise_ids\":[],\"timeframe\":null,\"deloitte_details\":null,\"is_escrow_project\":false,\"is_seller_kyc_required\":false,\"is_buyer_kyc_required\":false,\"local_details\":null,\"equipment\":null,\"nda_signatures_new\":null,\"billing_code\":null,\"enterprise_metadata_values\":null,\"project_reject_reason\":{\"description\":null,\"message\":null},\"repost_id\":null,\"client_engagement\":null,\"contract_signatures\":null,\"quotation_id\":null,\"quotation_version_id\":null,\"enterprise_linked_projects_details\":null,\"equipment_groups\":null,\"project_source\":null,\"project_source_reference\":null},{\"id\":33258352,\"owner_id\":61331301,\"title\":\"Re type a document into word\",\"status\":\"active\",\"sub_status\":null,\"seo_url\":\"word/type-document-into-word-33258352\",\"currency\":{\"id\":1,\"code\":\"USD\",\"sign\":\"$\",\"name\":\"US Dollar\",\"exchange_rate\":1.0,\"country\":\"US\",\"is_external\":false,\"is_escrowcom_supported\":true},\"description\":null,\"jobs\":[{\"id\":39,\"name\":\"Data Entry\",\"category\":{\"id\":4,\"name\":\"Data Entry & Admin\"},\"active_project_count\":null,\"seo_url\":\"data_entry\",\"seo_info\":null,\"local\":false},{\"id\":104,\"name\":\"Editing\",\"category\":{\"id\":2,\"name\":\"Writing & Content\"},\"active_project_count\":null,\"seo_url\":\"editing\",\"seo_info\":null,\"local\":false},{\"id\":158,\"name\":\"PDF\",\"category\":{\"id\":2,\"name\":\"Writing & Content\"},\"active_project_count\":null,\"seo_url\":\"pdf\",\"seo_info\":null,\"local\":false},{\"id\":197,\"name\":\"Word\",\"category\":{\"id\":3,\"name\":\"Design, Media & Architecture\"},\"active_project_count\":null,\"seo_url\":\"word\",\"seo_info\":null,\"local\":false},{\"id\":375,\"name\":\"Copy Typing\",\"category\":{\"id\":2,\"name\":\"Writing & Content\"},\"active_project_count\":null,\"seo_url\":\"copy_typing\",\"seo_info\":null,\"local\":false}],\"submitdate\":1647817351,\"preview_description\":\"I need a freelancer who can handle this task\",\"deleted\":false,\"nonpublic\":false,\"hidebids\":false,\"type\":\"fixed\",\"bidperiod\":7,\"budget\":{\"minimum\":250.0,\"maximum\":750.0,\"name\":null,\"project_type\":null,\"currency_id\":null},\"hourly_project_info\":null,\"featured\":false,\"urgent\":false,\"assisted\":null,\"active_prepaid_milestone\":null,\"bid_stats\":{\"bid_count\":7,\"bid_avg\":321.42857142857144},\"time_submitted\":1647817351,\"time_updated\":1647817351,\"upgrades\":{\"featured\":false,\"sealed\":false,\"nonpublic\":false,\"fulltime\":false,\"urgent\":false,\"qualified\":false,\"NDA\":false,\"assisted\":null,\"active_prepaid_milestone\":null,\"ip_contract\":false,\"success_bundle\":null,\"non_compete\":false,\"project_management\":false,\"pf_only\":false,\"recruiter\":null,\"listed\":null,\"extend\":null,\"unpaid_recruiter\":null},\"qualifications\":null,\"language\":\"en\",\"attachments\":null,\"hireme\":false,\"hireme_initial_bid\":null,\"invited_freelancers\":null,\"recommended_freelancers\":null,\"frontend_project_status\":\"open\",\"nda_signatures\":null,\"location\":{\"country\":{\"name\":null,\"flag_url\":null,\"code\":null,\"highres_flag_url\":null,\"flag_url_cdn\":null,\"highres_flag_url_cdn\":null,\"iso3\":null,\"region_id\":null,\"phone_code\":null,\"demonym\":null,\"person\":null,\"seo_url\":null,\"sanction\":null,\"language_code\":null,\"language_id\":null},\"city\":null,\"latitude\":null,\"longitude\":null,\"vicinity\":null,\"administrative_area\":null,\"full_address\":null,\"administrative_area_code\":null,\"postal_code\":null},\"true_location\":null,\"local\":false,\"negotiated\":false,\"negotiated_bid\":null,\"time_free_bids_expire\":1647820951,\"can_post_review\":null,\"files\":null,\"user_distance\":null,\"from_user_location\":null,\"project_collaborations\":null,\"support_sessions\":null,\"track_ids\":null,\"drive_files\":null,\"nda_details\":null,\"pool_ids\":[\"freelancer\"],\"enterprise_ids\":[],\"timeframe\":null,\"deloitte_details\":null,\"is_escrow_project\":false,\"is_seller_kyc_required\":false,\"is_buyer_kyc_required\":false,\"local_details\":null,\"equipment\":null,\"nda_signatures_new\":null,\"billing_code\":null,\"enterprise_metadata_values\":null,\"project_reject_reason\":{\"description\":null,\"message\":null},\"repost_id\":null,\"client_engagement\":null,\"contract_signatures\":null,\"quotation_id\":null,\"quotation_version_id\":null,\"enterprise_linked_projects_details\":null,\"equipment_groups\":null,\"project_source\":null,\"project_source_reference\":null},{\"id\":33258348,\"owner_id\":61330664,\"title\":\"Data entry PDF to Word\",\"status\":\"active\",\"sub_status\":null,\"seo_url\":\"data-entry/Data-entry-PDF-Word-33258348\",\"currency\":{\"id\":9,\"code\":\"CAD\",\"sign\":\"$\",\"name\":\"Canadian Dollar\",\"exchange_rate\":0.793486,\"country\":\"CA\",\"is_external\":false,\"is_escrowcom_supported\":false},\"description\":null,\"jobs\":[{\"id\":39,\"name\":\"Data Entry\",\"category\":{\"id\":4,\"name\":\"Data Entry & Admin\"},\"active_project_count\":null,\"seo_url\":\"data_entry\",\"seo_info\":null,\"local\":false},{\"id\":55,\"name\":\"Excel\",\"category\":{\"id\":4,\"name\":\"Data Entry & Admin\"},\"active_project_count\":null,\"seo_url\":\"excel\",\"seo_info\":null,\"local\":false},{\"id\":158,\"name\":\"PDF\",\"category\":{\"id\":2,\"name\":\"Writing & Content\"},\"active_project_count\":null,\"seo_url\":\"pdf\",\"seo_info\":null,\"local\":false},{\"id\":197,\"name\":\"Word\",\"category\":{\"id\":3,\"name\":\"Design, Media & Architecture\"},\"active_project_count\":null,\"seo_url\":\"word\",\"seo_info\":null,\"local\":false},{\"id\":375,\"name\":\"Copy Typing\",\"category\":{\"id\":2,\"name\":\"Writing & Content\"},\"active_project_count\":null,\"seo_url\":\"copy_typing\",\"seo_info\":null,\"local\":false}],\"submitdate\":1647817289,\"preview_description\":\"If you are interested in convert pdf to word project so send a msg on whtsapp +91 8377849433 With fu\",\"deleted\":false,\"nonpublic\":false,\"hidebids\":false,\"type\":\"fixed\",\"bidperiod\":7,\"budget\":{\"minimum\":3000.0,\"maximum\":5000.0,\"name\":null,\"project_type\":null,\"currency_id\":null},\"hourly_project_info\":null,\"featured\":false,\"urgent\":false,\"assisted\":null,\"active_prepaid_milestone\":null,\"bid_stats\":{\"bid_count\":1,\"bid_avg\":3000.0},\"time_submitted\":1647817289,\"time_updated\":1647817289,\"upgrades\":{\"featured\":false,\"sealed\":false,\"nonpublic\":false,\"fulltime\":false,\"urgent\":false,\"qualified\":false,\"NDA\":false,\"assisted\":null,\"active_prepaid_milestone\":null,\"ip_contract\":false,\"success_bundle\":null,\"non_compete\":false,\"project_management\":false,\"pf_only\":false,\"recruiter\":null,\"listed\":null,\"extend\":null,\"unpaid_recruiter\":null},\"qualifications\":null,\"language\":\"en\",\"attachments\":null,\"hireme\":false,\"hireme_initial_bid\":null,\"invited_freelancers\":null,\"recommended_freelancers\":null,\"frontend_project_status\":\"open\",\"nda_signatures\":null,\"location\":{\"country\":{\"name\":null,\"flag_url\":null,\"code\":null,\"highres_flag_url\":null,\"flag_url_cdn\":null,\"highres_flag_url_cdn\":null,\"iso3\":null,\"region_id\":null,\"phone_code\":null,\"demonym\":null,\"person\":null,\"seo_url\":null,\"sanction\":null,\"language_code\":null,\"language_id\":null},\"city\":null,\"latitude\":null,\"longitude\":null,\"vicinity\":null,\"administrative_area\":null,\"full_address\":null,\"administrative_area_code\":null,\"postal_code\":null},\"true_location\":null,\"local\":false,\"negotiated\":false,\"negotiated_bid\":null,\"time_free_bids_expire\":1647820889,\"can_post_review\":null,\"files\":null,\"user_distance\":null,\"from_user_location\":null,\"project_collaborations\":null,\"support_sessions\":null,\"track_ids\":null,\"drive_files\":null,\"nda_details\":null,\"pool_ids\":[\"freelancer\"],\"enterprise_ids\":[],\"timeframe\":null,\"deloitte_details\":null,\"is_escrow_project\":false,\"is_seller_kyc_required\":false,\"is_buyer_kyc_required\":false,\"local_details\":null,\"equipment\":null,\"nda_signatures_new\":null,\"billing_code\":null,\"enterprise_metadata_values\":null,\"project_reject_reason\":{\"description\":null,\"message\":null},\"repost_id\":null,\"client_engagement\":null,\"contract_signatures\":null,\"quotation_id\":null,\"quotation_version_id\":null,\"enterprise_linked_projects_details\":null,\"equipment_groups\":null,\"project_source\":null,\"project_source_reference\":null},{\"id\":33258346,\"owner_id\":61331290,\"title\":\"Brazilian words\",\"status\":\"active\",\"sub_status\":null,\"seo_url\":\"translation/Brazilian-words-33258346\",\"currency\":{\"id\":1,\"code\":\"USD\",\"sign\":\"$\",\"name\":\"US Dollar\",\"exchange_rate\":1.0,\"country\":\"US\",\"is_external\":false,\"is_escrowcom_supported\":true},\"description\":null,\"jobs\":[{\"id\":22,\"name\":\"Translation\",\"category\":{\"id\":2,\"name\":\"Writing & Content\"},\"active_project_count\":null,\"seo_url\":\"translation\",\"seo_info\":null,\"local\":true},{\"id\":519,\"name\":\"English (UK) Translator\",\"category\":{\"id\":10,\"name\":\"Translation & Languages\"},\"active_project_count\":null,\"seo_url\":\"english_uk_translator\",\"seo_info\":null,\"local\":false},{\"id\":536,\"name\":\"Portuguese (Brazil)\",\"category\":{\"id\":10,\"name\":\"Translation & Languages\"},\"active_project_count\":null,\"seo_url\":\"portuguese_brazil\",\"seo_info\":null,\"local\":false},{\"id\":537,\"name\":\"Portuguese\",\"category\":{\"id\":10,\"name\":\"Translation & Languages\"},\"active_project_count\":null,\"seo_url\":\"portuguese\",\"seo_info\":null,\"local\":false},{\"id\":561,\"name\":\"English (US) Translator\",\"category\":{\"id\":10,\"name\":\"Translation & Languages\"},\"active_project_count\":null,\"seo_url\":\"english_us_translator\",\"seo_info\":null,\"local\":false}],\"submitdate\":1647817262,\"preview_description\":\"I've a document in English I want a native\\nBrazil speaker to translate it from English to\\nBrazil ple\",\"deleted\":false,\"nonpublic\":false,\"hidebids\":false,\"type\":\"hourly\",\"bidperiod\":7,\"budget\":{\"minimum\":2.0,\"maximum\":8.0,\"name\":null,\"project_type\":null,\"currency_id\":null},\"hourly_project_info\":{\"commitment\":{\"hours\":40,\"interval\":\"week\"},\"duration_enum\":\"unspecified\"},\"featured\":false,\"urgent\":false,\"assisted\":null,\"active_prepaid_milestone\":null,\"bid_stats\":{\"bid_count\":1,\"bid_avg\":8.0},\"time_submitted\":1647817262,\"time_updated\":1647817262,\"upgrades\":{\"featured\":false,\"sealed\":false,\"nonpublic\":false,\"fulltime\":false,\"urgent\":false,\"qualified\":false,\"NDA\":false,\"assisted\":null,\"active_prepaid_milestone\":null,\"ip_contract\":false,\"success_bundle\":null,\"non_compete\":false,\"project_management\":false,\"pf_only\":false,\"recruiter\":null,\"listed\":null,\"extend\":null,\"unpaid_recruiter\":null},\"qualifications\":null,\"language\":\"en\",\"attachments\":null,\"hireme\":false,\"hireme_initial_bid\":null,\"invited_freelancers\":null,\"recommended_freelancers\":null,\"frontend_project_status\":\"open\",\"nda_signatures\":null,\"location\":{\"country\":{\"name\":null,\"flag_url\":null,\"code\":null,\"highres_flag_url\":null,\"flag_url_cdn\":null,\"highres_flag_url_cdn\":null,\"iso3\":null,\"region_id\":null,\"phone_code\":null,\"demonym\":null,\"person\":null,\"seo_url\":null,\"sanction\":null,\"language_code\":null,\"language_id\":null},\"city\":null,\"latitude\":null,\"longitude\":null,\"vicinity\":null,\"administrative_area\":null,\"full_address\":null,\"administrative_area_code\":null,\"postal_code\":null},\"true_location\":null,\"local\":false,\"negotiated\":false,\"negotiated_bid\":null,\"time_free_bids_expire\":1647824462,\"can_post_review\":null,\"files\":null,\"user_distance\":null,\"from_user_location\":null,\"project_collaborations\":null,\"support_sessions\":null,\"track_ids\":null,\"drive_files\":null,\"nda_details\":null,\"pool_ids\":[\"freelancer\"],\"enterprise_ids\":[],\"timeframe\":null,\"deloitte_details\":null,\"is_escrow_project\":false,\"is_seller_kyc_required\":false,\"is_buyer_kyc_required\":false,\"local_details\":null,\"equipment\":null,\"nda_signatures_new\":null,\"billing_code\":null,\"enterprise_metadata_values\":null,\"project_reject_reason\":{\"description\":null,\"message\":null},\"repost_id\":null,\"client_engagement\":null,\"contract_signatures\":null,\"quotation_id\":null,\"quotation_version_id\":null,\"enterprise_linked_projects_details\":null,\"equipment_groups\":null,\"project_source\":null,\"project_source_reference\":null},{\"id\":33258345,\"owner_id\":61331261,\"title\":\"Latin translation\",\"status\":\"active\",\"sub_status\":null,\"seo_url\":\"translation/Latin-translation-33258345\",\"currency\":{\"id\":4,\"code\":\"GBP\",\"sign\":\"\\u00a3\",\"name\":\"British Pounds\",\"exchange_rate\":1.316993,\"country\":\"UK\",\"is_external\":false,\"is_escrowcom_supported\":false},\"description\":null,\"jobs\":[{\"id\":22,\"name\":\"Translation\",\"category\":{\"id\":2,\"name\":\"Writing & Content\"},\"active_project_count\":null,\"seo_url\":\"translation\",\"seo_info\":null,\"local\":true},{\"id\":518,\"name\":\"German\",\"category\":{\"id\":10,\"name\":\"Translation & Languages\"},\"active_project_count\":null,\"seo_url\":\"german\",\"seo_info\":null,\"local\":false},{\"id\":519,\"name\":\"English (UK) Translator\",\"category\":{\"id\":10,\"name\":\"Translation & Languages\"},\"active_project_count\":null,\"seo_url\":\"english_uk_translator\",\"seo_info\":null,\"local\":false},{\"id\":555,\"name\":\"Hindi\",\"category\":{\"id\":10,\"name\":\"Translation & Languages\"},\"active_project_count\":null,\"seo_url\":\"hindi\",\"seo_info\":null,\"local\":false},{\"id\":561,\"name\":\"English (US) Translator\",\"category\":{\"id\":10,\"name\":\"Translation & Languages\"},\"active_project_count\":null,\"seo_url\":\"english_us_translator\",\"seo_info\":null,\"local\":false}],\"submitdate\":1647817246,\"preview_description\":\"Latin translator needed If you are interested in the work. kindly send a message on Telegram @Sophia\",\"deleted\":false,\"nonpublic\":false,\"hidebids\":false,\"type\":\"fixed\",\"bidperiod\":7,\"budget\":{\"minimum\":250.0,\"maximum\":750.0,\"name\":null,\"project_type\":null,\"currency_id\":null},\"hourly_project_info\":null,\"featured\":false,\"urgent\":false,\"assisted\":null,\"active_prepaid_milestone\":null,\"bid_stats\":{\"bid_count\":0,\"bid_avg\":null},\"time_submitted\":1647817246,\"time_updated\":1647817246,\"upgrades\":{\"featured\":false,\"sealed\":false,\"nonpublic\":false,\"fulltime\":false,\"urgent\":false,\"qualified\":false,\"NDA\":false,\"assisted\":null,\"active_prepaid_milestone\":null,\"ip_contract\":false,\"success_bundle\":null,\"non_compete\":false,\"project_management\":false,\"pf_only\":false,\"recruiter\":null,\"listed\":null,\"extend\":null,\"unpaid_recruiter\":null},\"qualifications\":null,\"language\":\"en\",\"attachments\":null,\"hireme\":false,\"hireme_initial_bid\":null,\"invited_freelancers\":null,\"recommended_freelancers\":null,\"frontend_project_status\":\"open\",\"nda_signatures\":null,\"location\":{\"country\":{\"name\":null,\"flag_url\":null,\"code\":null,\"highres_flag_url\":null,\"flag_url_cdn\":null,\"highres_flag_url_cdn\":null,\"iso3\":null,\"region_id\":null,\"phone_code\":null,\"demonym\":null,\"person\":null,\"seo_url\":null,\"sanction\":null,\"language_code\":null,\"language_id\":null},\"city\":null,\"latitude\":null,\"longitude\":null,\"vicinity\":null,\"administrative_area\":null,\"full_address\":null,\"administrative_area_code\":null,\"postal_code\":null},\"true_location\":null,\"local\":false,\"negotiated\":false,\"negotiated_bid\":null,\"time_free_bids_expire\":1647820846,\"can_post_review\":null,\"files\":null,\"user_distance\":null,\"from_user_location\":null,\"project_collaborations\":null,\"support_sessions\":null,\"track_ids\":null,\"drive_files\":null,\"nda_details\":null,\"pool_ids\":[\"freelancer\"],\"enterprise_ids\":[],\"timeframe\":null,\"deloitte_details\":null,\"is_escrow_project\":false,\"is_seller_kyc_required\":false,\"is_buyer_kyc_required\":false,\"local_details\":null,\"equipment\":null,\"nda_signatures_new\":null,\"billing_code\":null,\"enterprise_metadata_values\":null,\"project_reject_reason\":{\"description\":null,\"message\":null},\"repost_id\":null,\"client_engagement\":null,\"contract_signatures\":null,\"quotation_id\":null,\"quotation_version_id\":null,\"enterprise_linked_projects_details\":null,\"equipment_groups\":null,\"project_source\":null,\"project_source_reference\":null},{\"id\":33258344,\"owner_id\":61328656,\"title\":\"Enter data into Excel File\",\"status\":\"active\",\"sub_status\":null,\"seo_url\":\"excel/Enter-data-into-Excel-File-33258344\",\"currency\":{\"id\":1,\"code\":\"USD\",\"sign\":\"$\",\"name\":\"US Dollar\",\"exchange_rate\":1.0,\"country\":\"US\",\"is_external\":false,\"is_escrowcom_supported\":true},\"description\":null,\"jobs\":[{\"id\":36,\"name\":\"Data Processing\",\"category\":{\"id\":4,\"name\":\"Data Entry & Admin\"},\"active_project_count\":null,\"seo_url\":\"data_processing\",\"seo_info\":null,\"local\":false},{\"id\":39,\"name\":\"Data Entry\",\"category\":{\"id\":4,\"name\":\"Data Entry & Admin\"},\"active_project_count\":null,\"seo_url\":\"data_entry\",\"seo_info\":null,\"local\":false},{\"id\":55,\"name\":\"Excel\",\"category\":{\"id\":4,\"name\":\"Data Entry & Admin\"},\"active_project_count\":null,\"seo_url\":\"excel\",\"seo_info\":null,\"local\":false},{\"id\":158,\"name\":\"PDF\",\"category\":{\"id\":2,\"name\":\"Writing & Content\"},\"active_project_count\":null,\"seo_url\":\"pdf\",\"seo_info\":null,\"local\":false},{\"id\":197,\"name\":\"Word\",\"category\":{\"id\":3,\"name\":\"Design, Media & Architecture\"},\"active_project_count\":null,\"seo_url\":\"word\",\"seo_info\":null,\"local\":false}],\"submitdate\":1647817245,\"preview_description\":\"Hello if you are interested in convert pdf to word project so send me a message here. Need someone t\",\"deleted\":false,\"nonpublic\":false,\"hidebids\":false,\"type\":\"hourly\",\"bidperiod\":7,\"budget\":{\"minimum\":8.0,\"maximum\":15.0,\"name\":null,\"project_type\":null,\"currency_id\":null},\"hourly_project_info\":{\"commitment\":{\"hours\":40,\"interval\":\"week\"},\"duration_enum\":\"unspecified\"},\"featured\":false,\"urgent\":false,\"assisted\":null,\"active_prepaid_milestone\":null,\"bid_stats\":{\"bid_count\":1,\"bid_avg\":10.0},\"time_submitted\":1647817245,\"time_updated\":1647817245,\"upgrades\":{\"featured\":false,\"sealed\":false,\"nonpublic\":false,\"fulltime\":false,\"urgent\":false,\"qualified\":false,\"NDA\":false,\"assisted\":null,\"active_prepaid_milestone\":null,\"ip_contract\":false,\"success_bundle\":null,\"non_compete\":false,\"project_management\":false,\"pf_only\":false,\"recruiter\":null,\"listed\":null,\"extend\":null,\"unpaid_recruiter\":null},\"qualifications\":null,\"language\":\"en\",\"attachments\":null,\"hireme\":false,\"hireme_initial_bid\":null,\"invited_freelancers\":null,\"recommended_freelancers\":null,\"frontend_project_status\":\"open\",\"nda_signatures\":null,\"location\":{\"country\":{\"name\":null,\"flag_url\":null,\"code\":null,\"highres_flag_url\":null,\"flag_url_cdn\":null,\"highres_flag_url_cdn\":null,\"iso3\":null,\"region_id\":null,\"phone_code\":null,\"demonym\":null,\"person\":null,\"seo_url\":null,\"sanction\":null,\"language_code\":null,\"language_id\":null},\"city\":null,\"latitude\":null,\"longitude\":null,\"vicinity\":null,\"administrative_area\":null,\"full_address\":null,\"administrative_area_code\":null,\"postal_code\":null},\"true_location\":null,\"local\":false,\"negotiated\":false,\"negotiated_bid\":null,\"time_free_bids_expire\":1647813645,\"can_post_review\":null,\"files\":null,\"user_distance\":null,\"from_user_location\":null,\"project_collaborations\":null,\"support_sessions\":null,\"track_ids\":null,\"drive_files\":null,\"nda_details\":null,\"pool_ids\":[\"freelancer\"],\"enterprise_ids\":[],\"timeframe\":null,\"deloitte_details\":null,\"is_escrow_project\":false,\"is_seller_kyc_required\":false,\"is_buyer_kyc_required\":false,\"local_details\":null,\"equipment\":null,\"nda_signatures_new\":null,\"billing_code\":null,\"enterprise_metadata_values\":null,\"project_reject_reason\":{\"description\":null,\"message\":null},\"repost_id\":null,\"client_engagement\":null,\"contract_signatures\":null,\"quotation_id\":null,\"quotation_version_id\":null,\"enterprise_linked_projects_details\":null,\"equipment_groups\":null,\"project_source\":null,\"project_source_reference\":null},{\"id\":33258340,\"owner_id\":61330664,\"title\":\"Conversation pdf file into Word \",\"status\":\"active\",\"sub_status\":null,\"seo_url\":\"word/Conversation-pdf-file-into-Word-33258340\",\"currency\":{\"id\":3,\"code\":\"AUD\",\"sign\":\"$\",\"name\":\"Australian Dollar\",\"exchange_rate\":0.740678,\"country\":\"AU\",\"is_external\":false,\"is_escrowcom_supported\":true},\"description\":null,\"jobs\":[{\"id\":39,\"name\":\"Data Entry\",\"category\":{\"id\":4,\"name\":\"Data Entry & Admin\"},\"active_project_count\":null,\"seo_url\":\"data_entry\",\"seo_info\":null,\"local\":false},{\"id\":55,\"name\":\"Excel\",\"category\":{\"id\":4,\"name\":\"Data Entry & Admin\"},\"active_project_count\":null,\"seo_url\":\"excel\",\"seo_info\":null,\"local\":false},{\"id\":158,\"name\":\"PDF\",\"category\":{\"id\":2,\"name\":\"Writing & Content\"},\"active_project_count\":null,\"seo_url\":\"pdf\",\"seo_info\":null,\"local\":false},{\"id\":197,\"name\":\"Word\",\"category\":{\"id\":3,\"name\":\"Design, Media & Architecture\"},\"active_project_count\":null,\"seo_url\":\"word\",\"seo_info\":null,\"local\":false},{\"id\":375,\"name\":\"Copy Typing\",\"category\":{\"id\":2,\"name\":\"Writing & Content\"},\"active_project_count\":null,\"seo_url\":\"copy_typing\",\"seo_info\":null,\"local\":false}],\"submitdate\":1647817215,\"preview_description\":\"If you are interested in convert pdf to word project so send a msg on whtsapp +91 8377849433 With fu\",\"deleted\":false,\"nonpublic\":false,\"hidebids\":false,\"type\":\"hourly\",\"bidperiod\":7,\"budget\":{\"minimum\":15.0,\"maximum\":25.0,\"name\":null,\"project_type\":null,\"currency_id\":null},\"hourly_project_info\":{\"commitment\":{\"hours\":40,\"interval\":\"week\"},\"duration_enum\":\"unspecified\"},\"featured\":false,\"urgent\":false,\"assisted\":null,\"active_prepaid_milestone\":null,\"bid_stats\":{\"bid_count\":0,\"bid_avg\":null},\"time_submitted\":1647817215,\"time_updated\":1647817215,\"upgrades\":{\"featured\":false,\"sealed\":false,\"nonpublic\":false,\"fulltime\":false,\"urgent\":false,\"qualified\":false,\"NDA\":false,\"assisted\":null,\"active_prepaid_milestone\":null,\"ip_contract\":false,\"success_bundle\":null,\"non_compete\":false,\"project_management\":false,\"pf_only\":false,\"recruiter\":null,\"listed\":null,\"extend\":null,\"unpaid_recruiter\":null},\"qualifications\":null,\"language\":\"en\",\"attachments\":null,\"hireme\":false,\"hireme_initial_bid\":null,\"invited_freelancers\":null,\"recommended_freelancers\":null,\"frontend_project_status\":\"open\",\"nda_signatures\":null,\"location\":{\"country\":{\"name\":null,\"flag_url\":null,\"code\":null,\"highres_flag_url\":null,\"flag_url_cdn\":null,\"highres_flag_url_cdn\":null,\"iso3\":null,\"region_id\":null,\"phone_code\":null,\"demonym\":null,\"person\":null,\"seo_url\":null,\"sanction\":null,\"language_code\":null,\"language_id\":null},\"city\":null,\"latitude\":null,\"longitude\":null,\"vicinity\":null,\"administrative_area\":null,\"full_address\":null,\"administrative_area_code\":null,\"postal_code\":null},\"true_location\":null,\"local\":false,\"negotiated\":false,\"negotiated_bid\":null,\"time_free_bids_expire\":1647820815,\"can_post_review\":null,\"files\":null,\"user_distance\":null,\"from_user_location\":null,\"project_collaborations\":null,\"support_sessions\":null,\"track_ids\":null,\"drive_files\":null,\"nda_details\":null,\"pool_ids\":[\"freelancer\"],\"enterprise_ids\":[],\"timeframe\":null,\"deloitte_details\":null,\"is_escrow_project\":false,\"is_seller_kyc_required\":false,\"is_buyer_kyc_required\":false,\"local_details\":null,\"equipment\":null,\"nda_signatures_new\":null,\"billing_code\":null,\"enterprise_metadata_values\":null,\"project_reject_reason\":{\"description\":null,\"message\":null},\"repost_id\":null,\"client_engagement\":null,\"contract_signatures\":null,\"quotation_id\":null,\"quotation_version_id\":null,\"enterprise_linked_projects_details\":null,\"equipment_groups\":null,\"project_source\":null,\"project_source_reference\":null},{\"id\":33258336,\"owner_id\":61331263,\"title\":\"Copy typing  -- 26469 \",\"status\":\"active\",\"sub_status\":null,\"seo_url\":\"data-entry/Copy-typing-33258336\",\"currency\":{\"id\":1,\"code\":\"USD\",\"sign\":\"$\",\"name\":\"US Dollar\",\"exchange_rate\":1.0,\"country\":\"US\",\"is_external\":false,\"is_escrowcom_supported\":true},\"description\":null,\"jobs\":[{\"id\":39,\"name\":\"Data Entry\",\"category\":{\"id\":4,\"name\":\"Data Entry & Admin\"},\"active_project_count\":null,\"seo_url\":\"data_entry\",\"seo_info\":null,\"local\":false},{\"id\":55,\"name\":\"Excel\",\"category\":{\"id\":4,\"name\":\"Data Entry & Admin\"},\"active_project_count\":null,\"seo_url\":\"excel\",\"seo_info\":null,\"local\":false},{\"id\":158,\"name\":\"PDF\",\"category\":{\"id\":2,\"name\":\"Writing & Content\"},\"active_project_count\":null,\"seo_url\":\"pdf\",\"seo_info\":null,\"local\":false},{\"id\":197,\"name\":\"Word\",\"category\":{\"id\":3,\"name\":\"Design, Media & Architecture\"},\"active_project_count\":null,\"seo_url\":\"word\",\"seo_info\":null,\"local\":false},{\"id\":375,\"name\":\"Copy Typing\",\"category\":{\"id\":2,\"name\":\"Writing & Content\"},\"active_project_count\":null,\"seo_url\":\"copy_typing\",\"seo_info\":null,\"local\":false}],\"submitdate\":1647817147,\"preview_description\":\"Copy typing  -- 26469\\nRETYPE PDF FILE TO WORD FILES \\nneed to retype PDF files to word and make sure \",\"deleted\":false,\"nonpublic\":false,\"hidebids\":false,\"type\":\"hourly\",\"bidperiod\":7,\"budget\":{\"minimum\":15.0,\"maximum\":25.0,\"name\":null,\"project_type\":null,\"currency_id\":null},\"hourly_project_info\":{\"commitment\":{\"hours\":40,\"interval\":\"week\"},\"duration_enum\":\"unspecified\"},\"featured\":false,\"urgent\":false,\"assisted\":null,\"active_prepaid_milestone\":null,\"bid_stats\":{\"bid_count\":1,\"bid_avg\":20.0},\"time_submitted\":1647817147,\"time_updated\":1647817147,\"upgrades\":{\"featured\":false,\"sealed\":false,\"nonpublic\":false,\"fulltime\":false,\"urgent\":false,\"qualified\":false,\"NDA\":false,\"assisted\":null,\"active_prepaid_milestone\":null,\"ip_contract\":false,\"success_bundle\":null,\"non_compete\":false,\"project_management\":false,\"pf_only\":false,\"recruiter\":null,\"listed\":null,\"extend\":null,\"unpaid_recruiter\":null},\"qualifications\":null,\"language\":\"en\",\"attachments\":null,\"hireme\":false,\"hireme_initial_bid\":null,\"invited_freelancers\":null,\"recommended_freelancers\":null,\"frontend_project_status\":\"open\",\"nda_signatures\":null,\"location\":{\"country\":{\"name\":null,\"flag_url\":null,\"code\":null,\"highres_flag_url\":null,\"flag_url_cdn\":null,\"highres_flag_url_cdn\":null,\"iso3\":null,\"region_id\":null,\"phone_code\":null,\"demonym\":null,\"person\":null,\"seo_url\":null,\"sanction\":null,\"language_code\":null,\"language_id\":null},\"city\":null,\"latitude\":null,\"longitude\":null,\"vicinity\":null,\"administrative_area\":null,\"full_address\":null,\"administrative_area_code\":null,\"postal_code\":null},\"true_location\":null,\"local\":false,\"negotiated\":false,\"negotiated_bid\":null,\"time_free_bids_expire\":1647813547,\"can_post_review\":null,\"files\":null,\"user_distance\":null,\"from_user_location\":null,\"project_collaborations\":null,\"support_sessions\":null,\"track_ids\":null,\"drive_files\":null,\"nda_details\":null,\"pool_ids\":[\"freelancer\"],\"enterprise_ids\":[],\"timeframe\":null,\"deloitte_details\":null,\"is_escrow_project\":false,\"is_seller_kyc_required\":false,\"is_buyer_kyc_required\":false,\"local_details\":null,\"equipment\":null,\"nda_signatures_new\":null,\"billing_code\":null,\"enterprise_metadata_values\":null,\"project_reject_reason\":{\"description\":null,\"message\":null},\"repost_id\":null,\"client_engagement\":null,\"contract_signatures\":null,\"quotation_id\":null,\"quotation_version_id\":null,\"enterprise_linked_projects_details\":null,\"equipment_groups\":null,\"project_source\":null,\"project_source_reference\":null},{\"id\":33258297,\"owner_id\":40727549,\"title\":\"Create arabic logo\",\"status\":\"active\",\"sub_status\":null,\"seo_url\":\"logo-design/Create-arabic-logo-33258297\",\"currency\":{\"id\":1,\"code\":\"USD\",\"sign\":\"$\",\"name\":\"US Dollar\",\"exchange_rate\":1.0,\"country\":\"US\",\"is_external\":false,\"is_escrowcom_supported\":true},\"description\":null,\"jobs\":[{\"id\":20,\"name\":\"Graphic Design\",\"category\":{\"id\":3,\"name\":\"Design, Media & Architecture\"},\"active_project_count\":null,\"seo_url\":\"graphic_design\",\"seo_info\":null,\"local\":false},{\"id\":32,\"name\":\"Logo Design\",\"category\":{\"id\":3,\"name\":\"Design, Media & Architecture\"},\"active_project_count\":null,\"seo_url\":\"logo_design\",\"seo_info\":null,\"local\":false},{\"id\":57,\"name\":\"Photoshop\",\"category\":{\"id\":3,\"name\":\"Design, Media & Architecture\"},\"active_project_count\":null,\"seo_url\":\"photoshop\",\"seo_info\":null,\"local\":false},{\"id\":70,\"name\":\"Illustrator\",\"category\":{\"id\":3,\"name\":\"Design, Media & Architecture\"},\"active_project_count\":null,\"seo_url\":\"illustrator\",\"seo_info\":null,\"local\":false},{\"id\":554,\"name\":\"Arabic Translator\",\"category\":{\"id\":10,\"name\":\"Translation & Languages\"},\"active_project_count\":null,\"seo_url\":\"arabic_translator\",\"seo_info\":null,\"local\":false}],\"submitdate\":1647817115,\"preview_description\":\"We are preparing a charity event and need a logo for the day. It is for arabic audience. So the logo\",\"deleted\":false,\"nonpublic\":false,\"hidebids\":false,\"type\":\"fixed\",\"bidperiod\":7,\"budget\":{\"minimum\":10.0,\"maximum\":30.0,\"name\":null,\"project_type\":null,\"currency_id\":null},\"hourly_project_info\":null,\"featured\":false,\"urgent\":false,\"assisted\":null,\"active_prepaid_milestone\":null,\"bid_stats\":{\"bid_count\":27,\"bid_avg\":22.85185185185185},\"time_submitted\":1647817115,\"time_updated\":1647817115,\"upgrades\":{\"featured\":false,\"sealed\":false,\"nonpublic\":false,\"fulltime\":false,\"urgent\":false,\"qualified\":false,\"NDA\":false,\"assisted\":null,\"active_prepaid_milestone\":null,\"ip_contract\":false,\"success_bundle\":null,\"non_compete\":false,\"project_management\":false,\"pf_only\":false,\"recruiter\":null,\"listed\":null,\"extend\":null,\"unpaid_recruiter\":null},\"qualifications\":null,\"language\":\"en\",\"attachments\":null,\"hireme\":false,\"hireme_initial_bid\":null,\"invited_freelancers\":null,\"recommended_freelancers\":null,\"frontend_project_status\":\"open\",\"nda_signatures\":null,\"location\":{\"country\":{\"name\":null,\"flag_url\":null,\"code\":null,\"highres_flag_url\":null,\"flag_url_cdn\":null,\"highres_flag_url_cdn\":null,\"iso3\":null,\"region_id\":null,\"phone_code\":null,\"demonym\":null,\"person\":null,\"seo_url\":null,\"sanction\":null,\"language_code\":null,\"language_id\":null},\"city\":null,\"latitude\":null,\"longitude\":null,\"vicinity\":null,\"administrative_area\":null,\"full_address\":null,\"administrative_area_code\":null,\"postal_code\":null},\"true_location\":null,\"local\":false,\"negotiated\":false,\"negotiated_bid\":null,\"time_free_bids_expire\":1647813045,\"can_post_review\":null,\"files\":null,\"user_distance\":null,\"from_user_location\":null,\"project_collaborations\":null,\"support_sessions\":null,\"track_ids\":null,\"drive_files\":null,\"nda_details\":null,\"pool_ids\":[\"freelancer\"],\"enterprise_ids\":[],\"timeframe\":null,\"deloitte_details\":null,\"is_escrow_project\":false,\"is_seller_kyc_required\":false,\"is_buyer_kyc_required\":false,\"local_details\":null,\"equipment\":null,\"nda_signatures_new\":null,\"billing_code\":null,\"enterprise_metadata_values\":null,\"project_reject_reason\":{\"description\":null,\"message\":null},\"repost_id\":null,\"client_engagement\":null,\"contract_signatures\":null,\"quotation_id\":null,\"quotation_version_id\":null,\"enterprise_linked_projects_details\":null,\"equipment_groups\":null,\"project_source\":null,\"project_source_reference\":null}],\"users\":null,\"selected_bids\":null,\"total_count\":13036},\"request_id\":\"658b3154ff857c79c46dceb325ccef39\"}";
        JSONObject jsonNode = null;
        try {
            jsonNode = new JSONObject(jsonStr);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            FreeLancerServices freeLancerServices1=new FreeLancerServices();
           List<ProjectDetails>list= freeLancerServices1.searchSkillProjectsJson(jsonNode);
           List<ProjectDetails>projectDetails=freeLancerServices1.searchModelByKeywordJson(jsonNode);
           assertEquals(list.size(),10);
            assertEquals(projectDetails.size(),10);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    /**
     * Test the number of words in project description
     * @author Nipun Hedaoo
     */
    @Test
    public void getNumOfWords(){
        String projectDescription = "The Australian platypus is seemingly a hybrid of a mammal and reptilian creature.";
        FreeLancerServices freeLancerServicesObj = new FreeLancerServices();
        assertEquals(freeLancerServicesObj.getNumOfWords(projectDescription), 13);
        assertNotEquals(homeController.freelancerClient.getNumOfWords(projectDescription), 8);
    }

    /**
     * Test the number of sentences in project description
     * @author Nipun Hedaoo
     */
    @Test
    public void getNumOfSetences(){
        String projectDescription = "The Australian platypus is seemingly a hybrid of a mammal and reptilian creature.";
        FreeLancerServices freeLancerServicesObj = new FreeLancerServices();
        assertEquals(freeLancerServicesObj.getNumOfSentences(projectDescription), 1);
        assertNotEquals(freeLancerServicesObj.getNumOfSentences(projectDescription), 3);
    }


    /**
     * Test the number of syllables in project description
     * @author Nipun Hedaoo
     */
    @Test
    public void getNnumOfSyllables(){
        String projectDescription = "The Australian platypus is seemingly a hybrid of a mammal and reptilian creature.";

        FreeLancerServices freeLancerServicesObj = new FreeLancerServices();
        assertEquals(freeLancerServicesObj.getNnumOfSyllables(projectDescription), 25);
        assertNotEquals(freeLancerServicesObj.getNnumOfSyllables(projectDescription), 30);
    }



    /**
     * Test the flesch kancid grade level for project description
     * @author Nipun Hedaoo
     */
    @Test
    public void getfleschKancidGradeLevvel(){
        String projectDescription = "The Australian platypus is seemingly a hybrid of a mammal and reptilian creature.";

        FreeLancerServices freeLancerServicesObj = new FreeLancerServices();

        int numOfSentence = 0;
        int numOfWords = 0;
        int numOfSyllables = 0;

        numOfSentence = freeLancerServicesObj.getNumOfSentences(projectDescription);
        numOfWords = freeLancerServicesObj.getNumOfWords(projectDescription);
        numOfSyllables = freeLancerServicesObj.getNnumOfSyllables(projectDescription);

        assertEquals(0, Double.compare(freeLancerServicesObj.calculateFKGL(numOfSentence, numOfWords, numOfSyllables), 1.2800000000000011));
        assertNotEquals(1, Double.compare(freeLancerServicesObj.calculateFKGL(numOfSentence, numOfWords, numOfSyllables), 13.3));
    }

    /**
     * Test the readibility index for project description
     * @author Nipun Hedaoo
     */
    @Test
    public void getReadabilityIndex(){
        String projectDescription = "The Australian platypus is seemingly a hybrid of a mammal and reptilian creature.";
        FreeLancerServices freeLancerServicesObj = new FreeLancerServices();


        int numOfSentence = 0;
        int numOfWords = 0;
        int numOfSyllables = 0;

        numOfSentence = freeLancerServicesObj.getNumOfSentences(projectDescription);
        numOfWords = freeLancerServicesObj.getNumOfWords(projectDescription);
        numOfSyllables = freeLancerServicesObj.getNnumOfSyllables(projectDescription);

        assertEquals( 0, Double.compare(freeLancerServicesObj.calculateFRI(numOfSentence, numOfWords, numOfSyllables), 109.04000000000002));
        assertNotEquals(freeLancerServicesObj.calculateFRI(numOfSentence, numOfWords, numOfSyllables), 30);
    }

    /**
     * Test the flesch kancid grade level average for project description
     * @author Nipun Hedaoo
     */
    @Test
    public void fleschKancidGradeLevvelAverage(){
        List<ProjectDetails> projectDetails = new ArrayList<>();

        FreeLancerServices freeLancerServicesObj = new FreeLancerServices();
        List<List<String>> skills = new ArrayList<>();
        ProjectDetails project1 = new ProjectDetails(-1, -1, skills, 1, "title", "type", null, null, 0.0, 0.0, "Early");
        project1.setPreviewDescription("The cat sat on the mat.");
        ProjectDetails project2 = new ProjectDetails(-1, -1, skills, 1, "title", "type", null, null, 0.0, 0.0, "Early");
        project2.setPreviewDescription("This sentence, taken as a reading passage unto itself, is being used to prove a point.");
        ProjectDetails project3 = new ProjectDetails(-1, -1, skills, 1, "title", "type", null, null, 0.0, 0.0, "Early");
        project3.setPreviewDescription("The Australian platypus is seemingly a hybrid of a mammal and reptilian creature.");

        projectDetails.add(project1);
        projectDetails.add(project2);
        projectDetails.add(project3);

        assertEquals(0, Double.compare(freeLancerServicesObj.fleschKancidGradeLevvel(projectDetails).getAsDouble(), 0.6666666666666666));
        assertNotEquals(0, Double.compare(freeLancerServicesObj.fleschKancidGradeLevvel(projectDetails).getAsDouble(), 2.0));
        assertNotEquals(1, Double.compare(freeLancerServicesObj.fleschKancidGradeLevvel(projectDetails).getAsDouble(), 0.6666666666666666));

    }


    /**
     * Test the flesch readibility average for project description
     * @author Nipun Hedaoo
     */
    @Test
    public void fleschReadabilityAverage(){
        List<ProjectDetails> projectDetails = new ArrayList<>();
        FreeLancerServices freeLancerServicesObj = new FreeLancerServices();
        List<List<String>> skills = new ArrayList<>();
        ProjectDetails project1 = new ProjectDetails(-1, -1, skills, 1, "title", "type", null, null, 0.0, 0.0, "Early");
        project1.setPreviewDescription("The cat sat on the mat.");
        ProjectDetails project2 = new ProjectDetails(-1, -1, skills, 1, "title", "type", null, null, 0.0, 0.0, "Early");
        project2.setPreviewDescription("This sentence, taken as a reading passage unto itself, is being used to prove a point.");
        ProjectDetails project3 = new ProjectDetails(-1, -1, skills, 1, "title", "type", null, null, 0.0, 0.0, "Early");
        project3.setPreviewDescription("The Australian platypus is seemingly a hybrid of a mammal and reptilian creature.");

        projectDetails.add(project1);
        projectDetails.add(project2);
        projectDetails.add(project3);

        assertEquals(0, Double.compare(freeLancerServicesObj.readabilityIndex(projectDetails).getAsDouble(), 110.33333333333333));
        assertNotEquals(0, Double.compare(freeLancerServicesObj.readabilityIndex(projectDetails).getAsDouble(), 90));
        assertNotEquals(1, Double.compare(freeLancerServicesObj.readabilityIndex(projectDetails).getAsDouble(), 110.33333333333333));
        assertEquals(1, Double.compare(freeLancerServicesObj.readabilityIndex(projectDetails).getAsDouble(), 90));
    }



}
