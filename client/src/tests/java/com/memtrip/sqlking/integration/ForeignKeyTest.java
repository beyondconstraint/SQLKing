/**
 * Copyright 2013-present memtrip LTD.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.memtrip.sqlking.integration;

import android.database.Cursor;

import com.memtrip.sqlking.operation.function.Raw;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adrian Velcich [adrian@higration.co.za]
 */
public class ForeignKeyTest extends IntegrationTest
    {
    @Before
    public void setUp()
        {
        super.setUp();

        getSetupData().tearDownTestData(getSQLProvider());
        getSetupData().setupTestData(getSQLProvider());

        getSetupLog().tearDownTestLogs(getSQLProvider());
        getSetupLog().setupTestLogs(getSQLProvider());
        }

    @Test
    public void testUserForeignKeyIsCreated()
        {
        Cursor cursor = Raw.getBuilder()
                                .query("PRAGMA foreign_key_list('User');")
                                .execute(getSQLProvider());

        List<String> foreignKeys = getForeignKeys(cursor);

        assertEquals(1, foreignKeys.size());
        }

    private List<String> getForeignKeys(Cursor cursor)
        {
        List<String> foreignKeys = new ArrayList<>();

        try {
            while (cursor.moveToNext())
                {
                int foreignKey = cursor.getColumnIndex("name");

                if (foreignKey != -1)
                    {
                    String foreignKeyName = cursor.getString(foreignKey);
                    foreignKeys.add(foreignKeyName);
                    }
                }
            }
        finally
            {
            cursor.close();
            }

        return foreignKeys;
        }
    }
