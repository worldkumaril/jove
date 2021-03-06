/*
 * LangSchema-Jove - Programming Language Modeling Classes for Jove
 * Copyright (C) 2005 Newisys, Inc. or its licensors, as applicable.
 *
 * Licensed under the Open Software License version 2.0 (the "License"); you
 * may not use this file except in compliance with the License. You should
 * have received a copy of the License along with this software; if not, you
 * may obtain a copy of the License at
 *
 * http://opensource.org/licenses/osl-2.0.php
 *
 * This software is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.newisys.schemaprinter.jove;

import com.newisys.schemaprinter.java.BasePrinter;
import com.newisys.schemaprinter.java.ImportManager;
import com.newisys.schemaprinter.java.JavaSchemaPrinter;
import com.newisys.schemaprinter.java.SchemaObjectPrinter;
import com.newisys.util.text.TokenFormatter;

/**
 * Java schema printer with support for Jove extended types.
 * 
 * @author Trevor Robinson
 */
public class JoveSchemaPrinter
    extends JavaSchemaPrinter
{
    protected BasePrinter getBasePrinter(
        TokenFormatter fmt,
        ImportManager importMgr)
    {
        return new JoveBasePrinter(fmt, this, importMgr);
    }

    protected SchemaObjectPrinter getSchemaObjectPrinter(BasePrinter basePrinter)
    {
        return new JoveSchemaObjectPrinter(basePrinter);
    }
}
