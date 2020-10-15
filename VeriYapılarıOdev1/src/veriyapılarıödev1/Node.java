/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package veriyapılarıödev1;

/**
 *
 * @author X550V
 * @param <T>
 */
public class Node<T> {

    T data;
    Node<T> nextNode;
    boolean isNextNodeSpecial = false;

    public Node(T data) {
        this.data = data;
    }

}
