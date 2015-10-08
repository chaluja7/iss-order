package cz.cvut.iss.service;

import cz.cvut.iss.exception.BadOrderBodyException;
import cz.cvut.iss.exception.NoSuchItemException;
import cz.cvut.iss.exception.NoSuchOrderException;
import cz.cvut.iss.model.Order;
import cz.cvut.iss.model.ResolvedOrder;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author jakubchalupa
 * @since 30.09.15
 */
@WebService(name = "Order", serviceName = "orderService", targetNamespace = "urn:cz.cvut.iss:order")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface OrderService {

    @WebMethod(operationName = "create")
    @WebResult(name = "orderId")
    long create(@WebParam(name = "order") @XmlElement(required = true, nillable = false) Order order) throws BadOrderBodyException, NoSuchItemException;

    @WebMethod(operationName = "get")
    @WebResult(name = "order")
    ResolvedOrder get(@WebParam(name = "orderId") long orderId) throws NoSuchOrderException;

}
