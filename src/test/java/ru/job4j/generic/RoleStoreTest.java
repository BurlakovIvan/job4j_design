package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {
    @Test
    public void whenAddAndFindThenRoleRightAdd() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "add"));
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("add"));
    }

    @Test
    public void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "add"));
        Role result = store.findById("2");
        assertNull(result);
    }

    @Test
    public void whenAddDuplicateAndFindRoleNameIsAdd() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "add"));
        store.add(new Role("1", "delete"));
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("add"));
    }

    @Test
    public void whenReplaceThenRoleNameIsDelete() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "add"));
        store.replace("1", new Role("1", "delete"));
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("delete"));
    }

    @Test
    public void whenNoReplaceRoleThenNoChangeRoleName() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "add"));
        store.replace("2", new Role("2", "delete"));
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("add"));
    }

    @Test
    public void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "add"));
        store.delete("1");
        Role result = store.findById("1");
        assertNull(result);
    }

    @Test
    public void whenNoDeleteRoleThenRoleNameIsAdd() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "add"));
        store.delete("2");
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("add"));
    }
}