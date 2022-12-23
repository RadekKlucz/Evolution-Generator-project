package agh.project.Classes;

import java.util.ArrayList;
import java.util.Random;

public class Genes {
    protected ArrayList<Integer> genes;
    public int activeGenIndex = 0;

    public Genes(){
        this.genes = new ArrayList<>();
    }

    public void setStartGenes(int gensNumber){
        Random random = new Random();
        for(int i=0; i<gensNumber; i++){
            this.genes.add(random.nextInt(8));
        }
    }

    public void setChildGens(Animal fatherAnimal, Animal matherAnimal){
        int fatherEnergy = fatherAnimal.getEnergy();
        int matherEnergy = matherAnimal.getEnergy();
        int sumEnergy = fatherEnergy + matherEnergy;
        int gensSize = fatherAnimal.getGenes().lenght();

        float percentFatherAnimalGenes = (fatherEnergy / sumEnergy) * gensSize;
        float percentMatherAnimalGenes = (matherEnergy / sumEnergy) * gensSize;
        int strongAnimal;
        int weakAnimal;

        ArrayList<Integer> fatherGens =  fatherAnimal.getGenes().getGenes();
        ArrayList<Integer> matherGens =  fatherAnimal.getGenes().getGenes();



        if(fatherEnergy > matherEnergy){
            strongAnimal = fatherEnergy;
            weakAnimal = matherEnergy;
        }else {
            strongAnimal = matherEnergy;
            weakAnimal = fatherEnergy;
        }


        Random random = new Random();
        int side = random.nextInt(2);
        if (side == 0){
            if(fatherEnergy > matherEnergy){
                for(int i=0; i<percentFatherAnimalGenes; i++){
                    //pobież od ojca
                    this.genes.add(fatherGens.get(i));

                }
                for(int i=gensSize; i>percentMatherAnimalGenes; i--){
                    //pobież od matki
                    this.genes.add(matherGens.get(i));
                }
            }
        }else {
            if (matherEnergy > fatherEnergy) {
                for (int i = 0; i < percentFatherAnimalGenes; i++) {
                    //pobież od matki
                    this.genes.add(matherGens.get(i));

                }
                for (int i = gensSize; i > percentMatherAnimalGenes; i--) {
                    //pobież od ojca
                    this.genes.add(fatherGens.get(i));
                }
            }
        }
    }
    public int getActiveGenIndex() { /// to chyba jednak w animal
        return activeGenIndex;
    }

    public ArrayList<Integer> getGenes() {
        return genes;
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
