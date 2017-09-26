/*************************************************************************
 * (c) Copyright 2017 Hewlett Packard Enterprise Development Company LP
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 ************************************************************************/
package edu.ucsb.eucalyptus.msgs;

import org.jboss.netty.handler.codec.http.HttpResponseStatus;
import com.eucalyptus.component.annotation.ComponentMessage;
import com.eucalyptus.component.id.Eucalyptus;
import com.eucalyptus.util.Exceptions;

@ComponentMessage( Eucalyptus.class )
public class ExceptionResponseType extends BaseMessage {

  private String source = "not available";
  private String message = "not available";
  private String requestType = "not available";
  private Throwable exception;
  private String error = "not available";
  private HttpResponseStatus httpStatus = HttpResponseStatus.BAD_REQUEST;

  public ExceptionResponseType( ) {
  }

  public ExceptionResponseType( BaseMessage msg, String message, Throwable exception ) {
    this( msg, message, HttpResponseStatus.BAD_REQUEST, exception );
  }

  public ExceptionResponseType( BaseMessage msg, String message, HttpResponseStatus httpStatus, Throwable exception ) {
    this( msg, msg.getClass( ).getSimpleName( ), message, httpStatus, exception );
  }

  public ExceptionResponseType( BaseMessage msg, String requestType, String message, HttpResponseStatus httpStatus, Throwable exception ) {
    super( msg );
    this.httpStatus = httpStatus;
    this.source = exception.getClass( ).getCanonicalName( );
    final String exMessage = ( exception == null ? null : exception.getMessage( ) );
    final Class<? extends Throwable> clazz = ( exception == null ? null : exception.getClass( ) );
    final String name = ( clazz == null ? null : clazz.getSimpleName( ) );
    this.message = message!=null ? message : exMessage!=null ? exMessage : name!=null ? name : "Internal error";
    this.requestType = requestType;
    this.exception = exception;
    if ( this.exception != null ) {
      this.error = Exceptions.string( exception );
    } else {
      this.error = "";
    }

    this.set_return( false );
  }

  public int getHttpStatusCode( ) {
    return httpStatus.getCode( );
  }

  public String getSource( ) {
    return source;
  }

  public void setSource( String source ) {
    this.source = source;
  }

  public String getMessage( ) {
    return message;
  }

  public void setMessage( String message ) {
    this.message = message;
  }

  public String getRequestType( ) {
    return requestType;
  }

  public void setRequestType( String requestType ) {
    this.requestType = requestType;
  }

  public Throwable getException( ) {
    return exception;
  }

  public void setException( Throwable exception ) {
    this.exception = exception;
  }

  public String getError( ) {
    return error;
  }

  public void setError( String error ) {
    this.error = error;
  }

  public HttpResponseStatus getHttpStatus( ) {
    return httpStatus;
  }

  public void setHttpStatus( HttpResponseStatus httpStatus ) {
    this.httpStatus = httpStatus;
  }
}
