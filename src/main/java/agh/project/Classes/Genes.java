package agh.project.Classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Genes {
    protected ArrayList<Integer> genes;
    public int activeGenIndex = 0;

    public Genes(ArrayList<Integer> genes){
        this.genes = new ArrayList<>();
    }

    public int getActiveGenIndex() { /// to chyba jednak w animal
        return activeGenIndex;
    }

    private void changeActiveGenInex(int newIndex){ /// to chyba jednak w animal
        this.activeGenIndex = newIndex;
    }

    //lenght of Genes
    private int lenght(){
        return this.genes.size();
    }
    //it returns next index of gens
    private int nextIndex(int activeGenIndex){
        int lenght = this.genes.size();
        int next = activeGenIndex + 1;
        if (next > lenght){
            return 0;
        }
        else {
            return next;
        }
    }
    public int nextGen(int activeGenIndex){
        int nextIndex = nextIndex(activeGenIndex);
        int next = this.genes.get(nextIndex);

        //in 20% of uses return random gen
        Random random = new Random();
        if (random.nextDouble() < 0.2){
            nextIndex = random.nextInt(this.genes.size());
//            next =  this.genes.get(random.nextInt(this.genes.size())); // czy tutaj trzeba dodac 1 do size i odjąć 1 od całości???
            next = this.genes.get(nextIndex);
        }

        //changing active gen index
        changeActiveGenInex(nextIndex);

        return next;
    }

    public void mutation(){
        for(int gen: this.genes){
            Random random = new Random();
            int[] values = {-1,1};
            int index = random.nextInt(values.length);
            int v = values[index];

            gen = gen + v;
            if (gen < 0){
                gen = 7;
            }
            else if(gen > 7){
                gen = 0;
            }
        }
    }
}
