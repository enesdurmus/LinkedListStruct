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
public class listNode<T> extends Node {

    // Bu sınıf Node sınıfını extend ediyor ekstra olarak sayaç özelliği var.
    // Ayrıca nextNode 'u yine bir listNode olacak şekilde özelliği override edildi.
    
    listNode<T> nextNode;
    
    int sayac = 0;

    public listNode(T data) {
        super(data);
    }

}
