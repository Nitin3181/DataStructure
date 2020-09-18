package TreeCreation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Node {
    String data;
    Node left;
    Node right;

    public Node(String data){
        this.data = data;
    }

}
