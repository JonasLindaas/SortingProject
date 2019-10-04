package Processors;

import java.util.Collections;
import java.util.List;

public class CollectionsSort implements ISort {

    @Override
    public void sort(List input) {
        Collections.sort(input);
    }
}
