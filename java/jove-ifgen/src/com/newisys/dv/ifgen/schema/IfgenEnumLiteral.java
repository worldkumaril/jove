/*
 * Jove - The Open Verification Environment for the Java (TM) Platform
 * Copyright (C) 2005 Newisys, Inc. or its licensors, as applicable.
 * Java is a registered trademark of Sun Microsystems, Inc. in the U.S. or
 * other countries.
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

package com.newisys.dv.ifgen.schema;

import com.newisys.langschema.Literal;

/**
 * Ifgen schema object for enumeration element literals.
 * 
 * @author Jon Nall
 */
public final class IfgenEnumLiteral
    extends IfgenExpression
    implements Literal
{
    private final IfgenUnresolvedName unresolvedName;
    private IfgenEnumElement element;

    public IfgenEnumLiteral(IfgenSchema schema, IfgenUnresolvedName name)
    {
        super(schema);
        this.unresolvedName = name;
    }

    public void setElement(IfgenEnumElement element)
    {
        this.element = element;
    }

    public IfgenEnumElement getElement()
    {
        return element;
    }

    public IfgenUnresolvedName getUnresolvedName()
    {
        return this.unresolvedName;
    }

    @Override
    public IfgenType getType()
    {
        return getResultType();
    }

    @Override
    public void accept(IfgenSchemaObjectVisitor visitor)
    {
        visitor.visit(this);
    }

    public IfgenType getResultType()
    {
        return (element == null) ? null : element.getEnumType();
    }

    public boolean isConstant()
    {
        return true;
    }

}
