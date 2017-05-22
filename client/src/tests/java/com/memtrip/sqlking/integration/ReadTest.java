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

import com.memtrip.sqlking.gen.Q;
import com.memtrip.sqlking.integration.models.User;
import com.memtrip.sqlking.integration.utils.SetupUser;
import com.memtrip.sqlking.operation.clause.Where;
import com.memtrip.sqlking.operation.function.Select;
import com.memtrip.sqlking.operation.keyword.OrderBy;

import org.junit.Before;

import java.util.List;

import static com.memtrip.sqlking.operation.clause.And.and;
import static com.memtrip.sqlking.operation.clause.In.in;
import static com.memtrip.sqlking.operation.clause.Or.or;
import static com.memtrip.sqlking.operation.clause.Where.where;
import static org.junit.Assert.assertEquals;

/**
 * @author Samuel Kirton [sam@memtrip.com]
 */
public class ReadTest extends IntegrationTest {

    @Before
    public void setUp() {
        super.setUp();
        getSetupUser().tearDownFourTestUsers(getSQLProvider());
        getSetupUser().setupFourTestUsers(getSQLProvider());
    }

    @org.junit.Test
    public void testAllUsersAreSelected() {
        List<User> users = Select.getBuilder().execute(User.class, getSQLProvider());

        // 4 of the users created by #setupFourTestUsers will match the
        // exercise clause, therefore, we assert that 4 rows will be selected
        assertEquals(4, users.size());
    }

    @org.junit.Test
    public void testEqualToSingleSelection() {
        User user = Select.getBuilder()
                .where(where(Q.User.USERNAME, Where.Exp.EQUAL_TO, SetupUser.CLYDE_USER_NAME))
                .executeOne(User.class, getSQLProvider());

        assertEquals(SetupUser.CLYDE_USER_NAME, user.getUsername());
    }

    @org.junit.Test
    public void testEqualToBooleanSelection() {
        List<User> users = Select.getBuilder()
                .where(where(Q.User.IS_REGISTERED, Where.Exp.EQUAL_TO, true))
                .execute(User.class, getSQLProvider());

        // 2 of the users created by #setupFourTestUsers will match the
        // exercise clause, therefore, we assert that 2 rows will be selected
        assertEquals(2, users.size());
    }

    @org.junit.Test
    public void testEqualToLongSelection() {
        User user = Select.getBuilder()
                .where(where(Q.User.TIMESTAMP, Where.Exp.EQUAL_TO, SetupUser.CLYDE_TIMESTAMP))
                .executeOne(User.class, getSQLProvider());

        assertEquals(SetupUser.CLYDE_USER_NAME, user.getUsername());
        assertEquals(SetupUser.CLYDE_TIMESTAMP, user.getTimestamp());
        assertEquals(SetupUser.CLYDE_IS_REGISTERED, user.getIsRegistered());
    }

    @org.junit.Test
    public void testMoreThanSelection() {
        List<User> users = Select.getBuilder()
                .where(where(Q.User.TIMESTAMP, Where.Exp.MORE_THAN, SetupUser.CLYDE_TIMESTAMP))
                .execute(User.class, getSQLProvider());

        // 3 of the users created by #setupFourTestUsers will match the
        // exercise clause, therefore, we assert that 3 rows will be selected
        assertEquals(3, users.size());
    }

    @org.junit.Test
    public void testMoreThanOrEqualToSelection() {
        List<User> users = Select.getBuilder()
                .where(where(Q.User.TIMESTAMP, Where.Exp.MORE_THAN_OR_EQUAL_TO, SetupUser.CLYDE_TIMESTAMP))
                .execute(User.class, getSQLProvider());

        // All 4 of the users created by #setupFourTestUsers will match the
        // exercise clause, therefore, we assert that 4 rows will be selected
        assertEquals(4, users.size());
    }

    @org.junit.Test
    public void testLessThanSelection() {
        List<User> users = Select.getBuilder()
                .where(where(Q.User.TIMESTAMP, Where.Exp.LESS_THAN, SetupUser.ANGIE_TIMESTAMP))
                .execute(User.class, getSQLProvider());

        // 3 of the users created by #setupFourTestUsers will match the
        // exercise clause, therefore, we assert that 3 rows will be selected
        assertEquals(3, users.size());
    }

    @org.junit.Test
    public void testLessThanOrEqualToSelection() {
        List<User> users = Select.getBuilder()
                .where(where(Q.User.TIMESTAMP, Where.Exp.LESS_THAN_OR_EQUAL_TO, SetupUser.ANGIE_TIMESTAMP))
                .execute(User.class, getSQLProvider());

        // 4 of the users created by #setupFourTestUsers will match the
        // exercise clause, therefore, we assert that 4 rows will be selected
        assertEquals(4, users.size());
    }

    @org.junit.Test
    public void testLikeStartingWithSelection() {
        List<User> users = Select.getBuilder()
                .where(where(Q.User.USERNAME, Where.Exp.LIKE, "jo%"))
                .execute(User.class, getSQLProvider());

        // 1 of the users created by #setupFourTestUsers will match the
        // exercise clause, therefore, we assert that 1 rows will be selected
        assertEquals(1, users.size());
    }

    @org.junit.Test
    public void testLikeEndingWithSelection() {
        List<User> users = Select.getBuilder()
                .where(where(Q.User.USERNAME, Where.Exp.LIKE, "%e"))
                .execute(User.class, getSQLProvider());

        // 2 of the users created by #setupFourTestUsers will match the
        // exercise clause, therefore, we assert that 2 rows will be selected
        assertEquals(2, users.size());
    }

    @org.junit.Test
    public void testLikeContainingSelection() {
        List<User> users = Select.getBuilder()
                .where(where(Q.User.USERNAME, Where.Exp.LIKE, "%lyd%"))
                .execute(User.class, getSQLProvider());

        // 1 of the users created by #setupFourTestUsers will match the
        // exercise clause, therefore, we assert that 1 rows will be selected
        assertEquals(1, users.size());
    }

    @org.junit.Test
    public void testInStringSelection() {
        List<User> users = Select.getBuilder()
                .where(in(Q.User.USERNAME, SetupUser.CLYDE_USER_NAME, SetupUser.ANGIE_USER_NAME))
                .execute(User.class, getSQLProvider());

        // 2 of the users created by #setupFourTestUsers will match the
        // exercise clause, therefore, we assert that 2 rows will be selected
        assertEquals(2, users.size());
    }

    @org.junit.Test
    public void testInLongSelection() {
        List<User> users = Select.getBuilder()
                .where(in(Q.User.TIMESTAMP, SetupUser.CLYDE_TIMESTAMP, SetupUser.ANGIE_TIMESTAMP, SetupUser.GILL_TIMESTAMP))
                .execute(User.class, getSQLProvider());

        // 3 of the users created by #setupFourTestUsers will match the
        // exercise clause, therefore, we assert that 3 rows will be selected
        assertEquals(3, users.size());
    }

    @org.junit.Test
    public void testOrWhereInQueryIsBuiltFromClause() {
        List<User> users = Select.getBuilder()
                .where(or(
                        where(Q.User.USERNAME, Where.Exp.EQUAL_TO, SetupUser.CLYDE_USER_NAME),
                        in(Q.User.TIMESTAMP, SetupUser.GILL_TIMESTAMP, SetupUser.ANGIE_TIMESTAMP)
                ))
                .execute(User.class, getSQLProvider());


        // 3 of the users created by #setupFourTestUsers will match the
        // exercise clause, therefore, we assert that 3 rows will be selected
        assertEquals(3, users.size());
    }

    @org.junit.Test
    public void testAndEqualOperationsSelection() {
        List<User> users = Select.getBuilder()
                .where(and(
                        where(Q.User.USERNAME, Where.Exp.EQUAL_TO, SetupUser.CLYDE_USER_NAME),
                        where(Q.User.IS_REGISTERED, Where.Exp.EQUAL_TO, SetupUser.CLYDE_IS_REGISTERED),
                        where(Q.User.TIMESTAMP, Where.Exp.EQUAL_TO, SetupUser.CLYDE_TIMESTAMP)
                ))
                .execute(User.class, getSQLProvider());

        // 1 of the users created by #setupFourTestUsers will match the
        // exercise clause, therefore, we assert that 1 rows will be selected
        assertEquals(1, users.size());
    }

    @org.junit.Test
    public void testOrEqualOperationsSelection() {
        List<User> users = Select.getBuilder()
                .where(or(
                        where(Q.User.USERNAME, Where.Exp.EQUAL_TO, SetupUser.CLYDE_USER_NAME),
                        where(Q.User.USERNAME, Where.Exp.EQUAL_TO, SetupUser.ANGIE_USER_NAME)
                ))
                .execute(User.class, getSQLProvider());

        // 2 of the users created by #setupFourTestUsers will match the
        // exercise clause, therefore, we assert that 2 rows will be selected
        assertEquals(2, users.size());
    }

    @org.junit.Test
    public void testAndOrEqualsOperationsSelection() {
        List<User> users = Select.getBuilder()
                .where(
                        and(
                                or(
                                        where(Q.User.USERNAME, Where.Exp.EQUAL_TO, SetupUser.CLYDE_USER_NAME),
                                        where(Q.User.USERNAME, Where.Exp.EQUAL_TO, SetupUser.ANGIE_USER_NAME)
                                ),
                                and(
                                        where(Q.User.TIMESTAMP, Where.Exp.MORE_THAN_OR_EQUAL_TO, SetupUser.ANGIE_TIMESTAMP)
                                )
                        )
                )
                .execute(User.class, getSQLProvider());

        // 1 of the users created by #setupFourTestUsers will match the
        // exercise clause, therefore, we assert that 1 rows will be selected
        assertEquals(1, users.size());
    }

    @org.junit.Test
    public void testNumericOrderByAscSelection() {
        List<User> users = Select.getBuilder()
                .orderBy(Q.User.TIMESTAMP, OrderBy.Order.ASC)
                .execute(User.class, getSQLProvider());

        // clyde, gill, josh, angie is the timestamp ascending order of the users created
        // by #setupFourTestUsers, therefore, we assert that the rows will be
        // selected in this order
        assertEquals(4, users.size());
        assertEquals(SetupUser.CLYDE_USER_NAME, users.get(0).getUsername());
        assertEquals(SetupUser.GILL_USER_NAME, users.get(1).getUsername());
        assertEquals(SetupUser.JOSH_USER_NAME, users.get(2).getUsername());
        assertEquals(SetupUser.ANGIE_USER_NAME, users.get(3).getUsername());
    }

    @org.junit.Test
    public void testNumericOrderByDescSelection() {
        List<User> users = Select.getBuilder()
                .orderBy(Q.User.TIMESTAMP, OrderBy.Order.DESC)
                .execute(User.class, getSQLProvider());

        // angie, josh, gill, clyde is the timestamp descending order of the users created
        // by #setupFourTestUsers, therefore, we assert that the rows will be
        // selected in this order
        assertEquals(4, users.size());
        assertEquals(SetupUser.ANGIE_USER_NAME, users.get(0).getUsername());
        assertEquals(SetupUser.JOSH_USER_NAME, users.get(1).getUsername());
        assertEquals(SetupUser.GILL_USER_NAME, users.get(2).getUsername());
        assertEquals(SetupUser.CLYDE_USER_NAME, users.get(3).getUsername());
    }

    @org.junit.Test
    public void testAlphaOrderByAscSelection() {
        List<User> users = Select.getBuilder()
                .orderBy(Q.User.USERNAME, OrderBy.Order.ASC)
                .execute(User.class, getSQLProvider());

        // angie, clyde, gill, josh is the username ascending order of the users created
        // by #setupFourTestUsers, therefore, we assert that the rows will be
        // selected in this order
        assertEquals(4, users.size());
        assertEquals(SetupUser.ANGIE_USER_NAME, users.get(0).getUsername());
        assertEquals(SetupUser.CLYDE_USER_NAME, users.get(1).getUsername());
        assertEquals(SetupUser.GILL_USER_NAME, users.get(2).getUsername());
        assertEquals(SetupUser.JOSH_USER_NAME, users.get(3).getUsername());
    }

    @org.junit.Test
    public void testAlphaOrderByDescSelection() {
        List<User> users = Select.getBuilder()
                .orderBy(Q.User.USERNAME, OrderBy.Order.DESC)
                .execute(User.class, getSQLProvider());

        // josh, gill, clyde, angie is the username descending order of the users created
        // by #setupFourTestUsers, therefore, we assert that the rows will be
        // selected in this order
        assertEquals(4, users.size());
        assertEquals(SetupUser.JOSH_USER_NAME, users.get(0).getUsername());
        assertEquals(SetupUser.GILL_USER_NAME, users.get(1).getUsername());
        assertEquals(SetupUser.CLYDE_USER_NAME, users.get(2).getUsername());
        assertEquals(SetupUser.ANGIE_USER_NAME, users.get(3).getUsername());
    }

    @org.junit.Test
    public void testOrderByRandom() {
        List<User> users = Select.getBuilder()
                .orderBy(Q.User.USERNAME, OrderBy.Order.RANDOM)
                .execute(User.class, getSQLProvider());

        // just check that the results are returned and no error is thrown
        // TODO: do 100 random queries and ensure that at least one of the ordering is different
        assertEquals(4, users.size());
    }

    @org.junit.Test
    public void testLimitLowerBoundSelection() {
        List<User> users = Select.getBuilder()
                .limit(0, 2)
                .orderBy(Q.User.USERNAME, OrderBy.Order.DESC)
                .execute(User.class, getSQLProvider());

        assertEquals(2, users.size());
        assertEquals(SetupUser.JOSH_USER_NAME, users.get(0).getUsername());
        assertEquals(SetupUser.GILL_USER_NAME, users.get(1).getUsername());
    }

    @org.junit.Test
    public void testLimitUpperBoundSelection() {
        List<User> users = Select.getBuilder()
                .limit(2,4)
                .orderBy(Q.User.USERNAME, OrderBy.Order.DESC)
                .execute(User.class, getSQLProvider());

        assertEquals(2, users.size());
        assertEquals(SetupUser.CLYDE_USER_NAME, users.get(0).getUsername());
        assertEquals(SetupUser.ANGIE_USER_NAME, users.get(1).getUsername());
    }
}