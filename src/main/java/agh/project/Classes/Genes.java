package agh.project.Classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Genes {
    protected ArrayList<Integer> genes;
    public int activeGenIndex = 1;

    public Genes(){
        this.genes = new ArrayList<>();
    }

    public void setStartGenes(int gensNumber){
        Random random = new Random();
        for(int i=0; i<gensNumber; i++){
            this.genes.add(random.nextInt(8));
        }
    }

    @Override
    public String toString() {
        return "Genes{" +
                "genes=" + genes +
                '}';
    }

    public void setChildGens(Animal fatherAnimal, Animal matherAnimal){


        float fatherEnergy = fatherAnimal.getEnergy();
        float matherEnergy = matherAnimal.getEnergy();

        float strongAnimal;
        float weakAnimal;

        Animal strongerAnimalObject;
        Animal weakerAnimalObject;

        if(fatherEnergy > matherEnergy){
            strongAnimal = fatherEnergy;
            strongerAnimalObject = fatherAnimal;
            weakAnimal = matherEnergy;
            weakerAnimalObject = matherAnimal;
        }else {
            strongAnimal = matherEnergy;
            strongerAnimalObject = matherAnimal;
            weakAnimal = fatherEnergy;
            weakerAnimalObject = fatherAnimal;
        }

        float sumEnergy = fatherEnergy + matherEnergy;
        int gensSize = fatherAnimal.getGenes().lenght();

        System.out.println("PIERWSZE CZTERY WARTOŚCI:");
        System.out.println(fatherEnergy);
        System.out.println(matherEnergy);
        System.out.println(sumEnergy);
        System.out.println(gensSize);

//        float percentFatherAnimalGenes = (fatherEnergy / sumEnergy) * gensSize;
//        float percentMatherAnimalGenes = (matherEnergy / sumEnergy) * gensSize;
//        percentFatherAnimalGenes = Math.round(percentFatherAnimalGenes);
//        percentMatherAnimalGenes = Math.round(percentMatherAnimalGenes);

        float StrongAnimalGenesNumber = ((strongAnimal / sumEnergy) * gensSize);
        float WeakAnimalGenesNumber = ((weakAnimal / sumEnergy) * gensSize);
        StrongAnimalGenesNumber = Math.round(StrongAnimalGenesNumber);
        WeakAnimalGenesNumber = Math.round(WeakAnimalGenesNumber);


//        if(StrongAnimalGenesNumber == WeakAnimalGenesNumber){
//            StrongAnimalGenesNumber ++;
//            WeakAnimalGenesNumber --;
//        }

        if(StrongAnimalGenesNumber + WeakAnimalGenesNumber > fatherAnimal.getGenes().lenght()){
            StrongAnimalGenesNumber --;
        }

        System.out.println("PROCENTY:");
        System.out.println(StrongAnimalGenesNumber);
        System.out.println(WeakAnimalGenesNumber);

//        float strongAnimal;
//        float weakAnimal;

        ArrayList<Integer> strongerAnimalGens =  strongerAnimalObject.getGenes().getGenes();
        ArrayList<Integer> weakerAnimalGens =  weakerAnimalObject.getGenes().getGenes();

        System.out.println("GENY FATHER AND MOTHER");
        System.out.println(strongerAnimalGens);
        System.out.println(weakerAnimalGens);
        System.out.println(strongerAnimalGens.size());
        System.out.println(weakerAnimalGens.size());


//        if(fatherEnergy > matherEnergy){
//            strongAnimal = fatherEnergy;
//            weakAnimal = matherEnergy;
//        }else {
//            strongAnimal = matherEnergy;
//            weakAnimal = fatherEnergy;
//        }

        Random random = new Random();
        int side = random.nextInt(2);

        if(side == 0){
            for(int i=0; i<StrongAnimalGenesNumber; i++){
//                    //pobież od mocniejszego
                    this.genes.add(strongerAnimalGens.get(i));
            }

            for(int i=gensSize-1; i>((gensSize-1) - WeakAnimalGenesNumber); i--){
                    //pobież od matki
                    this.genes.add(weakerAnimalGens.get(i));
                }
        }else {
            for(int i=0; i<WeakAnimalGenesNumber; i++){
                    //pobież od mocniejszego
                this.genes.add(weakerAnimalGens.get(i));
            }
            for(int i=gensSize-1; i>((gensSize-1) - StrongAnimalGenesNumber); i--){
                //pobież od matki
                this.genes.add(strongerAnimalGens.get(i));
            }
        }




//        Random random = new Random();
//        int side = random.nextInt(2);
//        if (side == 0){
//            if(fatherEnergy > matherEnergy){
//
//                for(int i=0; i<percentFatherAnimalGenes; i++){
//                    //pobież od ojca
//                    this.genes.add(fatherGens.get(i));
//
//                }
//                for(int i=gensSize; i>percentMatherAnimalGenes; i--){
//                    //pobież od matki
//                    this.genes.add(matherGens.get(i));
//                }
//            }
//        }else {
//            if (matherEnergy > fatherEnergy) {
//                for (int i = 0; i < percentFatherAnimalGenes; i++) {
//                    //pobież od matki
//                    this.genes.add(matherGens.get(i));
//
//                }
//                for (int i = gensSize; i > percentMatherAnimalGenes; i--) {
//                    //pobież od ojca
//                    this.genes.add(fatherGens.get(i));
//                }
//            }
//        }
        System.out.println("DLUGOSC GENOW SETCHILD GENS");
        System.out.println(this.lenght());
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
        if (next >= lenght){
            return 0;
        }
        else {
            return next;
        }
    }
    public int nextGen(int activeGenIndex){
        int nextIndex = nextIndex(activeGenIndex);
//        System.out.println("GEN INDEX:");
//        System.out.println(nextIndex);
        int next = this.genes.get(nextIndex);
//        System.out.println("NEXT GEN:");
//        System.out.println(next);

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
//        Random random = new Random();
//        int n = random.nextInt(8);  /// wczytywane z pliku
//        int min = 0;
//        int max = 7;

//        IntStream stream = IntStream.generate(() -> new Random().nextInt((min - max) + 1)+min)
//                .distinct()
//                .limit(n);
//        List<Integer> indexesList = stream.boxed().toList();


        Random random = new Random();
        int n = random.nextInt(1,8);
        int min = 0;
        int max = this.lenght()-1;

        IntStream stream = IntStream.generate(() -> random.nextInt((max - min) + 1) + min)
                .distinct()
                .limit(n);
        List<Integer> indexesList = stream.boxed().toList();

        System.out.println("INDEX LIST:");
        System.out.println(indexesList);
        for(int i: indexesList){
            int[] values = {-1,1};
            int index = random.nextInt(values.length);
            int v = values[index];

            System.out.println("GENES LENGHT");
            System.out.println(this.genes.size());
            this.genes.set(i, this.genes.get(i) + v);  //////////////////////////////////////////
            if (this.genes.get(i) < 0){
                this.genes.set(i, 7);
            }
            else if(this.genes.get(i) > 7){
                this.genes.set(i, 0);
            }
        }

//        for(int gen: this.genes){
//            Random random = new Random();
//            int[] values = {-1,1};
//            int index = random.nextInt(values.length);
//            int v = values[index];
//
//            gen = gen + v;
//            if (gen < 0){
//                gen = 7;
//            }
//            else if(gen > 7){
//                gen = 0;
//            }
//        }
    }
}
