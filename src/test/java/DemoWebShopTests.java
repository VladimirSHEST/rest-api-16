import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class DemoWebShopTests {
    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = "https://demowebshop.tricentis.com";
    }
    @Test
    void addToCartTest(){
        given()
//                .header("Content-Type","application/x-www-form-urlencoded; charset=UTF-8")
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .cookie("NOPCOMMERCE.AUTH", "13224D1D105D58CB0545B293273D6B57DC8DB75ADC3CEB4E31216C32A584D46AA85A08B5646271B45" +
                        "91D1FEA4AF68F1CA9A0CD63EC2D9B61C33A28673061AD70DE78D5AE8A71CEB0671AA9B65FBF5E5AF61F6929E4259F51FD580660520BF7" +
                        "371C8F789C99F7377690EE2D428D09DED4FF87179E1BF615669E082133845932A42C5C4A846C4948A7FE8ADF319C7EFF422BCD2344F571" +
                        "2D30CDF9FB2A186F2D40B73DCB8D3CE373649D56D542FDF83066; ")
                .body("product_attribute_72_5_18=65&product_attribute_72_6_19=55&product_attribute_72_3_20=58&product_attribute_72" +
                        "_8_30=95&addtocart_72.EnteredQuantity=3")
                .when()
                .post("/addproducttocart/details/72/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("success",is(true))
                .body("message",is("The product has been added to your <a href=\"/cart\">shopping cart</a>"));


    }
    @Test
    void addToCartTestAnonym() {
        given()
//
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")

                .body("product_attribute_72_5_18=65&product_attribute_72_6_19=55&product_attribute_72_3_20=58&product_attribute_72" +
                        "_8_30=95&addtocart_72.EnteredQuantity=3")
                .when()
                .post("/addproducttocart/details/72/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>")).
                body("updatetopcartsectionhtml", is("(3)"));
    }
}
