package com.baeldung.listvsset;

import static com.vladmihalcea.sql.SQLStatementCountValidator.assertDeleteCount;
import static com.vladmihalcea.sql.SQLStatementCountValidator.assertSelectCount;
import static com.vladmihalcea.sql.SQLStatementCountValidator.reset;
import static org.assertj.core.api.Assertions.assertThat;

import com.baeldung.listvsset.eager.set.moderatedomain.Application;
import com.baeldung.listvsset.eager.set.moderatedomain.Group;
import com.baeldung.listvsset.eager.set.moderatedomain.GroupService;
import com.baeldung.listvsset.eager.set.moderatedomain.User;
import com.baeldung.listvsset.util.TestConfig;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {Application.class, TestConfig.class}, properties = {
  "hibernate.show_sql=true",
  "logging.level.org.hibernate.SQL=debug",
  "logging.level.org.hibernate.orm.jdbc.bind=trace"
})
class NPlusOneEagerModerateSetIntegrationTest extends BaseNPlusOneIntegrationTest<User> {

    private static final int ONE = 1;

    @Autowired
    private GroupService groupService;

    @Test
    void givenEagerSetBasedUser_whenFetchingAllUsers_thenIssueNPlusOneRequests() {
        List<User> users = getService().findAll();
        assertSelectCount(users.size() + 1);
    }

    @ParameterizedTest
    @ValueSource(longs = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    void givenEagerSetBasedUser_whenFetchingOneUser_thenIssueNPlusOneRequests(Long id) {
        getService().getUserById(id);
        assertSelectCount(ONE);
    }

    @Test
    void givenEagerSetBasedGroup_whenFetchingAllGroups_thenIssueRequestsForEachGroup() {
        List<Group> groups = groupService.findAll();
        assertSelectCount(groups.size() + 1);
    }

    @ParameterizedTest
    @ValueSource(longs = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    void givenEagerSetBasedGroup_whenFetchingAllGroups_thenCreateCartesianProductInOneQuery(Long groupId) {
        groupService.findById(groupId);
        assertSelectCount(1);
    }

    @ParameterizedTest
    @ValueSource(longs = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    void givenEagerListBasedGroup_whenRemoveUser_thenIssueOnlyOneDelete(Long groupId) {
        Optional<Group> optionalGroup = groupService.findById(groupId);
        assertThat(optionalGroup).isPresent();
        Group group = optionalGroup.get();
        if (!group.getMembers().isEmpty()) {
            reset();
            System.out.println("\n\n\n\n\n");
            Set<User> newMembers = group.getMembers().stream().skip(ONE).collect(Collectors.toSet());
            group.setMembers(newMembers);
            groupService.save(group);
            assertSelectCount(ONE);
            assertDeleteCount(ONE);
        }
    }

    protected void addUsers() {
        List<User> users = jsonUtils.getUsers(User.class);
        databaseUtil.saveAll(users);
        List<Group> groups = jsonUtils.getGroupsWithMembers(Group.class);
        databaseUtil.saveAll(groups);
    }

}
