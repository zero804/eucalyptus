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
package com.eucalyptus.compute.common.network;

import java.util.ArrayList;
import com.google.common.collect.Lists;

public class PrepareNetworkResourcesType extends NetworkingMessage {

  private String availabilityZone;
  private String vpc;
  private String subnet;
  private ArrayList<NetworkResource> resources = Lists.newArrayList( );
  private ArrayList<NetworkFeature> features = Lists.newArrayList( );

  public PrepareNetworkResourcesType( ) {
  }

  public PrepareNetworkResourcesType(
      final String vpc,
      final String subnet,
      final ArrayList<NetworkResource> resources
  ) {
    this.vpc = vpc;
    this.subnet = subnet;
    this.resources = resources;
  }

  public String getAvailabilityZone( ) {
    return availabilityZone;
  }

  public void setAvailabilityZone( String availabilityZone ) {
    this.availabilityZone = availabilityZone;
  }

  public String getVpc( ) {
    return vpc;
  }

  public void setVpc( String vpc ) {
    this.vpc = vpc;
  }

  public String getSubnet( ) {
    return subnet;
  }

  public void setSubnet( String subnet ) {
    this.subnet = subnet;
  }

  public ArrayList<NetworkResource> getResources( ) {
    return resources;
  }

  public void setResources( ArrayList<NetworkResource> resources ) {
    this.resources = resources;
  }

  public ArrayList<NetworkFeature> getFeatures( ) {
    return features;
  }

  public void setFeatures( ArrayList<NetworkFeature> features ) {
    this.features = features;
  }
}
