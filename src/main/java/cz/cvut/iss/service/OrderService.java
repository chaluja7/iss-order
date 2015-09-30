package cz.cvut.iss.service;

import cz.cvut.iss.exception.NoSuchOrderException;
import cz.cvut.iss.model.Order;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * @author jakubchalupa
 * @since 30.09.15
 */
@WebService(name = "Order", serviceName = "orderService", targetNamespace = "urn:cz.cvut.iss:order")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface OrderService {

    @WebMethod(operationName = "get")
    @WebResult(name = "order")
    Order get(@WebParam(name = "orderId") long orderId) throws NoSuchOrderException;

}
