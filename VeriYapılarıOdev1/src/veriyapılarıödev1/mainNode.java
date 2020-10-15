/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package veriyapılarıödev1;

/**
 *
 * @author X550V
 */
public class mainNode<T> extends Node {

    
    // Bu sınıf Node sınıfını extend ediyor ekstra olarak listNode tutuyor.
    // Ayrıca nextNode 'u yine bir mainNode olacak şekilde özelliği override edildi.
    
    
    listNode<T> listNode;
    
    mainNode<T> nextNode;

    public mainNode(Object data) {
        super(data);
    }

}
