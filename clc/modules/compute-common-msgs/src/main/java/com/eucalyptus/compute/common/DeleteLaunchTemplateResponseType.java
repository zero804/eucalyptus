/*
 * Copyright 2018 AppScale Systems, Inc
 *
 * SPDX-License-Identifier: BSD-2-Clause
 */
package com.eucalyptus.compute.common;


public class DeleteLaunchTemplateResponseType extends VmControlMessage {

  private LaunchTemplate launchTemplate;

  public LaunchTemplate getLaunchTemplate( ) {
    return launchTemplate;
  }

  public void setLaunchTemplate( final LaunchTemplate launchTemplate ) {
    this.launchTemplate = launchTemplate;
  }

}
