package com.baeldung.shallowdeep;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RoleUnitTest {

    @Nested
    @DisplayName( "Role.clone() tests" )
    class CloneTest {

        @Test
        void whenCloningThis_thenExpectThisNotSameAsClone() {

            Role role = new Role("Software Engineer" );

            Role cRole = null;

            try {
                cRole = role.clone();
            } catch( CloneNotSupportedException e ) {
                e.printStackTrace();
            }

            assertNotSame( role, cRole );
        }

        @Test
        void whenCloningThis_thenExpectThisClassSameAsCloneClass() {

            Role role = new Role( "Software Engineer" );

            Role cRole = null;

            try {
                cRole = role.clone();
            } catch ( CloneNotSupportedException e ) {
                e.printStackTrace();
            }

            assert cRole != null;
            assertSame( role.getClass(), cRole.getClass() );

        }

    }

}
