
package com.bbva.findim.web.exception;

public class WSException extends BaseException {

    private static final long serialVersionUID = -4938556712560828963L;

    //Constructores ...

    public WSException() {
        super();
    }

    public WSException(Exception objException) {
        super(objException);
    }

    public WSException(String msjError) {
        super(msjError);
    }
    

    public WSException(String codError, String msjError,
                       Exception objException) {
        super(codError, msjError, objException);
    }

    public WSException(String codError, String msjError, String nombreWS,
                       Exception objException) {
        super(codError, msjError, nombreWS, objException);
    }

}

