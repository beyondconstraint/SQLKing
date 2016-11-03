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
package com.memtrip.sqlking.operation.join;

import com.memtrip.sqlking.operation.clause.Clause;

/**
 * @author Samuel Kirton [sam@memtrip.com]
 */
public abstract class Join<J> {
    private Class<J> mTable;
    private String mTableAlias;
    private Join mJoin;
    private Clause[] mClauses;

    public Class<J> getTable() {
        return mTable;
    }

    public Join getJoin() {
        return mJoin;
    }

    public Clause[] getClauses() {
        return mClauses;
    }

    public String getTableAliasName() { return mTableAlias; }

    public Join(Class<J> table, Clause... clauses) {
        mTable = table;
        mClauses = clauses;
    }

    public Join(Class<J> table, Join join, Clause... clauses) {
        mTable = table;
        mJoin = join;
        mClauses = clauses;
    }

    public Join(Class<J> table, String tableAlias, Clause... clauses) {
        mTable = table;
        mTableAlias = tableAlias;
        mClauses = clauses;
    }

    public Join(Class<J> table, String tableAlias, Join join, Clause... clauses) {
        mTable = table;
        mTableAlias = tableAlias;
        mJoin = join;
        mClauses = clauses;
    }
}