package de.tekup.soap.models.Endpoint;

import de.tekup.soap.models.Service.ExamService;
import de.tekup.soap.models.whitetest.ExamRequest;
import de.tekup.soap.models.whitetest.ExamResponse;
import de.tekup.soap.models.whitetest.StudentRequest;
import de.tekup.soap.models.whitetest.WhiteTestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class ExamEndpoint {
    @Autowired
    private ExamService examService;
    public static final String NAMESPACE = "http://www.tekup.de/soap/models/whitetest";

    @PayloadRoot(namespace = NAMESPACE, localPart = "StudentRequest")
    @ResponsePayload
    public WhiteTestResponse getWhiteTest(@RequestPayload StudentRequest studentRequest)  throws Exception{
        return examService.getExam(studentRequest);
    }

    @PayloadRoot(namespace = NAMESPACE , localPart = "ExamRequest")
    @ResponsePayload
    public ExamResponse getExams(@RequestPayload ExamRequest request){
        return examService.findAllExams(request);
    }

}
