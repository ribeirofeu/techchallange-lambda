package auth;

import auth.io.AuthPolicy;
import auth.service.AuthService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayCustomAuthorizerEvent;

/**
 * Handler for requests to Lambda function.
 */
public class App implements RequestHandler<APIGatewayCustomAuthorizerEvent, AuthPolicy> {

    public AuthPolicy handleRequest(final APIGatewayCustomAuthorizerEvent input, final Context context) {

        try {

            String cpf = input.getHeaders().get("cpf");
            System.out.println("cpf " + cpf);

            String methodArn = input.getMethodArn();
            String[] arnPartials = methodArn.split(":");
            String region = arnPartials[3];
            String awsAccountId = arnPartials[4];

            String[] apiGatewayArnPartials = arnPartials[5].split("/");
            String restApiId = apiGatewayArnPartials[0];
            String stage = apiGatewayArnPartials[1];
            String httpMethod = apiGatewayArnPartials[2];
            String resource = ""; // root resource
            if (apiGatewayArnPartials.length == 4) {
                resource = apiGatewayArnPartials[3];
            }

            AuthService authService = new AuthService();
            if (authService.validateAccess(cpf)){
                System.out.println("Access Allowed");
                return new AuthPolicy(cpf, AuthPolicy.PolicyDocument.getAllowAllPolicy(region, awsAccountId, restApiId, stage));
            }
            System.out.println("Access Denied");
            return new AuthPolicy(cpf, AuthPolicy.PolicyDocument.getDenyAllPolicy(region, awsAccountId, restApiId, stage));

        }catch (Exception e ){
            throw new RuntimeException("Unauthorized");
        }
    }
}
