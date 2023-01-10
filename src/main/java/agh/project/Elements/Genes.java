package agh.project.Elements;

import agh.project.Elements.Animal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class Genes {
    protected ArrayList<Integer> genes;
    public int activeGenIndex = 1;

    public Genes() {
        this.genes = new ArrayList<>();
    }

    public void setStartGenes(int gensNumber) {
        Random random = new Random();
        for (int i = 0; i < gensNumber; i++) {
            this.genes.add(random.nextInt(8));
        }
    }

    @Override
    public String toString() {
        return "Genes{" +
                "genes=" + genes +
                '}';
    }

    public void setChildGens(Animal fatherAnimal, Animal matherAnimal) {
        float fatherEnergy = fatherAnimal.getEnergy();
        float matherEnergy = matherAnimal.getEnergy();

        float strongAnimal;
        float weakAnimal;

        Animal strongerAnimalObject;
        Animal weakerAnimalObject;

        if (fatherEnergy > matherEnergy) {
            strongAnimal = fatherEnergy;
            strongerAnimalObject = fatherAnimal;
            weakAnimal = matherEnergy;
            weakerAnimalObject = matherAnimal;
        } else {
            strongAnimal = matherEnergy;
            strongerAnimalObject = matherAnimal;
            weakAnimal = fatherEnergy;
            weakerAnimalObject = fatherAnimal;
        }

        float sumEnergy = fatherEnergy + matherEnergy;
        int gensSize = fatherAnimal.getGenes().lenght();

        float StrongAnimalGenesNumber = ((strongAnimal / sumEnergy) * gensSize);
        float WeakAnimalGenesNumber = ((weakAnimal / sumEnergy) * gensSize);
        StrongAnimalGenesNumber = Math.round(StrongAnimalGenesNumber);
        WeakAnimalGenesNumber = Math.round(WeakAnimalGenesNumber);

        if (StrongAnimalGenesNumber + WeakAnimalGenesNumber > fatherAnimal.getGenes().lenght()) {
            StrongAnimalGenesNumber--;
        }

        ArrayList<Integer> strongerAnimalGens = strongerAnimalObject.getGenes().getGenes();
        ArrayList<Integer> weakerAnimalGens = weakerAnimalObject.getGenes().getGenes();


        Random random = new Random();
        int side = random.nextInt(2);

        if (side == 0) {
            for (int i = 0; i < StrongAnimalGenesNumber; i++) {
                //pobież od mocniejszego
                this.genes.add(strongerAnimalGens.get(i));
            }

            for (int i = gensSize - 1; i > ((gensSize - 1) - WeakAnimalGenesNumber); i--) {
                //pobież od słabszego
                this.genes.add(weakerAnimalGens.get(i));
            }
        } else {
            for (int i = 0; i < WeakAnimalGenesNumber; i++) {
                //pobież od mocniejszego
                this.genes.add(weakerAnimalGens.get(i));
            }
            for (int i = gensSize - 1; i > ((gensSize - 1) - StrongAnimalGenesNumber); i--) {
                //pobież od słabszego
                this.genes.add(strongerAnimalGens.get(i));
            }
        }
    }

    public int getActiveGenIndex() {
        return activeGenIndex;
    }

    public ArrayList<Integer> getGenes() {
        return genes;
    }

    private void changeActiveGenInex(int newIndex) { /// to chyba jednak w animal
        this.activeGenIndex = newIndex;
    }

    //lenght of Genes
    private int lenght() {
        return this.genes.size();
    }

    //it returns next index of gens
    private int nextIndex(int activeGenIndex) {
        int lenght = this.genes.size();
        int next = activeGenIndex + 1;
        if (next >= lenght) {
            return 0;
        } else {
            return next;
        }
    }

    public int nextGen(int activeGenIndex) {
        int nextIndex = nextIndex(activeGenIndex);
        int next = this.genes.get(nextIndex);

        //in 20% of uses return random gen
        Random random = new Random();
        if (random.nextDouble() < 0.2) {
            nextIndex = random.nextInt(this.genes.size());
            next = this.genes.get(nextIndex);
        }

        //changing active gen index
        changeActiveGenInex(nextIndex);

        return next;
    }

    public void mutation() {
        Random random = new Random();
        int n = random.nextInt(1, 8);
        int min = 0;
        int max = this.lenght() - 1;

        IntStream stream = IntStream.generate(() -> random.nextInt((max - min) + 1) + min)
                .distinct()
                .limit(n);
        List<Integer> indexesList = stream.boxed().toList();
        for (int i : indexesList) {
            int[] values = {-1, 1};
            int index = random.nextInt(values.length);
            int v = values[index];

            this.genes.set(i, this.genes.get(i) + v);
            if (this.genes.get(i) < 0) {
                this.genes.set(i, 7);
            } else if (this.genes.get(i) > 7) {
                this.genes.set(i, 0);
            }
        }
    }
}
