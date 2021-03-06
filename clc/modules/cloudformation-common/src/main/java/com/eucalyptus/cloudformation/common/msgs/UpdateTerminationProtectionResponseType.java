/*
 * Copyright 2020 AppScale Systems, Inc
 *
 * Use of this source code is governed by a BSD-2-Clause
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/BSD-2-Clause
 */
package com.eucalyptus.cloudformation.common.msgs;

public class UpdateTerminationProtectionResponseType extends CloudFormationMessage {

  private UpdateTerminationProtectionResult result = new UpdateTerminationProtectionResult();

  private ResponseMetadata responseMetadata = new ResponseMetadata();

  public ResponseMetadata getResponseMetadata() {
    return responseMetadata;
  }

  public void setResponseMetadata(final ResponseMetadata responseMetadata) {
    this.responseMetadata = responseMetadata;
  }

  public UpdateTerminationProtectionResult getUpdateTerminationProtectionResult() {
    return result;
  }

  public void setUpdateTerminationProtectionResult(final UpdateTerminationProtectionResult result) {
    this.result = result;
  }

}
