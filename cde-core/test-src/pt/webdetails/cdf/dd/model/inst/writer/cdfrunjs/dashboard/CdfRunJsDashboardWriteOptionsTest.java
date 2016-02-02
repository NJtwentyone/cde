/*!
 * Copyright 2002 - 2016 Webdetails, a Pentaho company. All rights reserved.
 *
 * This software was developed by Webdetails and is provided under the terms
 * of the Mozilla Public License, Version 2.0, or any later version. You may not use
 * this file except in compliance with the license. If you need a copy of the license,
 * please go to http://mozilla.org/MPL/2.0/. The Initial Developer is Webdetails.
 *
 * Software distributed under the Mozilla Public License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. Please refer to
 * the license for the specific language governing your rights and limitations.
 */

package pt.webdetails.cdf.dd.model.inst.writer.cdfrunjs.dashboard;


import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;
import pt.webdetails.cdf.dd.CdeConstants;

public class CdfRunJsDashboardWriteOptionsTest extends TestCase {

  @Test
  public void testAlias() {
    String emptyAlias = "";
    String normalAlias1 = "DashboardName_SomeAlias";
    String normalAlias2 = "DashboardName_" + CdeConstants.DASHBOARD_ALIAS_TAG;
    String spacedAlias1 = "Dashboard Name_";
    String spacedAlias2 = spacedAlias1 + CdeConstants.DASHBOARD_ALIAS_TAG;
    String prefix = "spaced prefix";
    String spacedAlias3 = spacedAlias1 + CdeConstants.DASHBOARD_ALIAS_TAG + prefix;
    String messyAlias = "I-[~!@#$%^&*(){}|.,]-=_+|;'\"?<>~`";

    Assert.assertEquals( "Alias is empty", emptyAlias, createAndGetAlias( emptyAlias ) );
    Assert.assertEquals( "Alias correctly set", normalAlias1, createAndGetAlias( normalAlias1 ) );
    Assert.assertEquals( "Alias correctly set", normalAlias2, createAndGetAlias( normalAlias2 ) );
    Assert.assertEquals( "Alias correctly set", "id_" + spacedAlias1.replace( " ", "32" ),
      createAndGetAlias( spacedAlias1 ) );

    Assert.assertTrue( createAndGetAlias( spacedAlias2 ).startsWith( "id_" + spacedAlias1.replace( " ", "32" ) ) );
    Assert.assertTrue( createAndGetAlias( spacedAlias2 ).endsWith( CdeConstants.DASHBOARD_ALIAS_TAG ) );

    Assert.assertTrue( createAndGetAlias( spacedAlias3 ).startsWith( "id_" + spacedAlias1.replace( " ", "32" ) ) );
    Assert.assertTrue( createAndGetAlias( spacedAlias3 ).endsWith( prefix.replace( " ", "32" ) ) );
    Assert.assertTrue( createAndGetAlias( spacedAlias3 ).contains( CdeConstants.DASHBOARD_ALIAS_TAG ) );

    Assert.assertEquals( "id_I-9112633643536379438424041123125124464493-61_4312459393463606212696",
      createAndGetAlias( messyAlias ) );
  }

  private String createAndGetAlias( String alias ) {
    return new CdfRunJsDashboardWriteOptions( alias, false, false, false, "", "" ).getAliasPrefix();
  }

}
