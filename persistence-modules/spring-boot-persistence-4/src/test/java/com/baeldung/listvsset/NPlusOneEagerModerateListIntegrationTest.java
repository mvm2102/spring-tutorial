package com.baeldung.listvsset;

import static com.vladmihalcea.sql.SQLStatementCountValidator.assertDeleteCount;
import static com.vladmihalcea.sql.SQLStatementCountValidator.assertInsertCount;
import static com.vladmihalcea.sql.SQLStatementCountValidator.assertSelectCount;
import static com.vladmihalcea.sql.SQLStatementCountValidator.reset;
import static org.assertj.core.api.Assertions.assertThat;

import com.baeldung.listvsset.eager.list.moderatedomain.Application;
import com.baeldung.listvsset.eager.list.moderatedomain.Group;
import com.baeldung.listvsset.eager.list.moderatedomain.GroupService;
import com.baeldung.listvsset.eager.list.moderatedomain.User;
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
class NPlusOneEagerModerateListIntegrationTest extends BaseNPlusOneIntegrationTest<User> {

    private static final int ONE = 1;

    @Autowired
    private GroupService groupService;

    @Test
    void givenEagerListBasedUser_whenFetchingAllUsers_thenIssueNPlusOneRequests() {
        List<User> users = getService().findAll();
        assertSelectCount(users.size() + 1);
    }

    @ParameterizedTest
    @ValueSource(longs = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    void givenEagerListBasedUser_whenFetchingOneUser_thenIssueNPlusOneRequests(Long id) {
        getService().getUserById(id);
        assertSelectCount(ONE);
    }

    @Test
    void givenEagerListBasedGroup_whenFetchingAllGroups_thenIssueRequestsGroupsPlusUsers() {
        List<Group> groups = groupService.findAll();
        Set<User> users = groups.stream().map(Group::getMembers).flatMap(List::stream).collect(Collectors.toSet());
        assertSelectCount(groups.size() + users.size() + 1);
    }

    @ParameterizedTest
    @ValueSource(longs = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    void givenEagerListBasedGroup_whenFetchingAllGroups_thenIssueRequestsForEveryUsers(Long groupId) {
        Optional<Group> group = groupService.findById(groupId);
        assertThat(group).isPresent();
        assertSelectCount(1 + group.get().getMembers().size());
    }
    @ParameterizedTest
    @ValueSource(longs = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    void givenEagerListBasedGroup_whenRemoveUser_thenIssueRecreateGroup(Long groupId) {
        Optional<Group> optionalGroup = groupService.findById(groupId);
        assertThat(optionalGroup).isPresent();
        Group group = optionalGroup.get();
        List<User> members = group.getMembers();
        assertSelectCount(ONE + members.size());
        if (!members.isEmpty()) {
            reset();
            System.out.println("\n\n\n\n\n");
            members.remove(0);
            groupService.save(group);
            assertSelectCount(ONE + members.size() + 1);
            assertDeleteCount(ONE);
            assertInsertCount(members.size());
        }
    }



    protected void addUsers() {
        List<User> users = jsonUtils.getUsers(User.class);
        databaseUtil.saveAll(users);
        List<Group> groups = jsonUtils.getGroupsWithMembers(Group.class);
        databaseUtil.saveAll(groups);
    }

}
