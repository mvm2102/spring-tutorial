package com.baeldung.junit4.runfromjava;

import org.junit.Test;

import com.baeldung.junit.runfromjava.listnode.ListNode;
import com.baeldung.junit.runfromjava.listnode.SwapNodes;

import junit.framework.TestCase;

public class SwapNodesUnitTest extends TestCase {
    private SwapNodes swapNodes;
    private ListNode listNode;

    public SwapNodesUnitTest() {
    }

    public SwapNodesUnitTest(String name) {
        super(name);
    }

    public void setUp() throws Exception {
        swapNodes = new SwapNodes();
        listNode = new ListNode(42, new ListNode(666, new ListNode(15, new ListNode(3, null))));
    }

    @Test
    public void testwhenSwappingPairs_thenReturnExpectedList() {
        assertEquals(swapNodes.swapPairs(listNode)
            .toString(), "666->42->3->15");
    }

}
