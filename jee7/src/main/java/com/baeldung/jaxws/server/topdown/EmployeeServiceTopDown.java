
package com.baeldung.jaxws.server.topdown;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "EmployeeServiceTopDown", targetNamespace = "http://topdown.server.jaxws.baeldung.com/")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface EmployeeServiceTopDown {


    /**
     * 
     * @return
     *     returns int
     */
    @WebMethod(action = "http://topdown.server.jaxws.baeldung.com/EmployeeServiceTopDown/countEmployees")
    @WebResult(name = "countEmployeesResponse", targetNamespace = "http://topdown.server.jaxws.baeldung.com/", partName = "parameters")
    public int countEmployees();

}
