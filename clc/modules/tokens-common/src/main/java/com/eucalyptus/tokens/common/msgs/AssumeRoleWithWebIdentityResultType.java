/*************************************************************************
 * Copyright 2009-2014 Ent. Services Development Corporation LP
 *
 * Redistribution and use of this software in source and binary forms,
 * with or without modification, are permitted provided that the
 * following conditions are met:
 *
 *   Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 *   Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer
 *   in the documentation and/or other materials provided with the
 *   distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 * FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 * COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN
 * ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 ************************************************************************/
package com.eucalyptus.tokens.common.msgs;

import edu.ucsb.eucalyptus.msgs.EucalyptusData;

public class AssumeRoleWithWebIdentityResultType extends EucalyptusData {
  private CredentialsType credentials;
  private AssumedRoleUserType assumedRoleUser;
  private Integer packedPolicySize;
  private String audience;
  private String provider;
  private String subjectFromWebIdentityToken;

  public AssumeRoleWithWebIdentityResultType() {
  }

  public CredentialsType getCredentials() {
    return credentials;
  }

  public void setCredentials( CredentialsType credentials ) {
    this.credentials = credentials;
  }

  public AssumedRoleUserType getAssumedRoleUser() {
    return assumedRoleUser;
  }

  public void setAssumedRoleUser( AssumedRoleUserType assumedRoleUser ) {
    this.assumedRoleUser = assumedRoleUser;
  }

  public Integer getPackedPolicySize() {
    return packedPolicySize;
  }

  public void setPackedPolicySize( Integer packedPolicySize ) {
    this.packedPolicySize = packedPolicySize;
  }

  public String getAudience() {
    return audience;
  }

  public void setAudience( String audience ) {
    this.audience = audience;
  }

  public String getProvider() {
    return provider;
  }

  public void setProvider( String provider ) {
    this.provider = provider;
  }

  public String getSubjectFromWebIdentityToken() {
    return subjectFromWebIdentityToken;
  }

  public void setSubjectFromWebIdentityToken( String subjectFromWebIdentityToken ) {
    this.subjectFromWebIdentityToken = subjectFromWebIdentityToken;
  }
}
