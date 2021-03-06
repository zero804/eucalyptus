/*
 * Copyright 2020 AppScale Systems, Inc
 *
 * Use of this source code is governed by a BSD-2-Clause
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/BSD-2-Clause
 */
package com.eucalyptus.route53.common.msgs;

import javax.annotation.Nonnull;
import com.eucalyptus.binding.HttpNoContent;
import com.eucalyptus.binding.HttpParameterMapping;
import com.eucalyptus.binding.HttpRequestMapping;
import com.eucalyptus.route53.common.Route53MessageValidation.FieldRange;
import com.eucalyptus.route53.common.Route53MessageValidation.FieldRegex;
import com.eucalyptus.route53.common.Route53MessageValidation.FieldRegexValue;


@HttpRequestMapping(method = "GET", uri = "/2013-04-01/trafficpolicyinstances/hostedzone")
@HttpNoContent
public class ListTrafficPolicyInstancesByHostedZoneType extends Route53Message {

  @Nonnull
  @HttpParameterMapping(parameter = "id")
  @FieldRange(max = 32)
  private String hostedZoneId;

  @HttpParameterMapping(parameter = "maxitems")
  private String maxItems;

  @HttpParameterMapping(parameter = "trafficpolicyinstancename")
  @FieldRange(max = 1024)
  private String trafficPolicyInstanceNameMarker;

  @HttpParameterMapping(parameter = "trafficpolicyinstancetype")
  @FieldRegex(FieldRegexValue.ENUM_RRTYPE)
  private String trafficPolicyInstanceTypeMarker;

  public String getHostedZoneId() {
    return hostedZoneId;
  }

  public void setHostedZoneId(final String hostedZoneId) {
    this.hostedZoneId = hostedZoneId;
  }

  public String getMaxItems() {
    return maxItems;
  }

  public void setMaxItems(final String maxItems) {
    this.maxItems = maxItems;
  }

  public String getTrafficPolicyInstanceNameMarker() {
    return trafficPolicyInstanceNameMarker;
  }

  public void setTrafficPolicyInstanceNameMarker(final String trafficPolicyInstanceNameMarker) {
    this.trafficPolicyInstanceNameMarker = trafficPolicyInstanceNameMarker;
  }

  public String getTrafficPolicyInstanceTypeMarker() {
    return trafficPolicyInstanceTypeMarker;
  }

  public void setTrafficPolicyInstanceTypeMarker(final String trafficPolicyInstanceTypeMarker) {
    this.trafficPolicyInstanceTypeMarker = trafficPolicyInstanceTypeMarker;
  }

}
