/*
 * Copyright 2004-2009 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.doma.domain;

import org.seasar.doma.domain.DomainException;
import org.seasar.doma.domain.StringDomain;

import junit.framework.TestCase;

public class StringDomainTest extends TestCase {

    public void testGet() throws Exception {
        StringDomain domain = new StringDomain("aaa");
        assertEquals("aaa", domain.get());
    }

    public void testSet_value() throws Exception {
        StringDomain domain = new StringDomain("aaa");
        domain.set("bbb");
        assertEquals("bbb", domain.get());
    }

    public void testSet_domain() throws Exception {
        StringDomain domain = new StringDomain("aaa");
        domain.setDomain(new StringDomain("bbb"));
        assertEquals("bbb", domain.get());
    }

    public void testIsEmpty() throws Exception {
        assertTrue(new StringDomain("").isEmpty());
        try {
            new StringDomain().isEmpty();
        } catch (DomainException ignored) {
            System.out.println(ignored.getMessage());
        }
    }

    public void testEquals() throws Exception {
        StringDomain domain = new StringDomain("aaa");
        StringDomain domain2 = new StringDomain("aaa");
        assertTrue(domain.equals(domain2));

        domain.setNull();
        assertFalse(domain.equals(domain2));

        domain2.setNull();
        assertTrue(domain.equals(domain2));

        domain.set("aaa");
        assertFalse(domain.equals(domain2));
    }

}
