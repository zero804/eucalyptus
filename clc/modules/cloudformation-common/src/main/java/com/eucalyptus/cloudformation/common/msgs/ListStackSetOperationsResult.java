/*
 * Copyright 2020 AppScale Systems, Inc
 *
 * Use of this source code is governed by a BSD-2-Clause
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/BSD-2-Clause
 */
package com.eucalyptus.cloudformation.common.msgs;

import com.eucalyptus.cloudformation.common.CloudFormationMessageValidation.FieldRange;
import edu.ucsb.eucalyptus.msgs.EucalyptusData;


public class ListStackSetOperationsResult extends EucalyptusData {

  @FieldRange(min = 1, max = 1024)
  private String nextToken;

  private StackSetOperationSummaries summaries;

  public String getNextToken() {
    return nextToken;
  }

  public void setNextToken(final String nextToken) {
    this.nextToken = nextToken;
  }

  public StackSetOperationSummaries getSummaries() {
    return summaries;
  }

  public void setSummaries(final StackSetOperationSummaries summaries) {
    this.summaries = summaries;
  }

}
