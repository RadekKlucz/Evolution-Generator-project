package agh.project.Classes;

import agh.project.Interfaces.IWorldMap;

import java.util.*;
import java.util.stream.Collectors;

public class engin  {
    private List<Animal> animals;
    private List<Plant> plants;

    private final IWorldMap map;

    public engin(IWorldMap map){
        this.map = map;
    }


    public void run(){
        //skręt i przemieszczenie każdego zwierzęcia,
        for (Animal animal : animals){
            animal.move();
        }
        //konsumpcja roślin na których pola weszły zwierzęta,
        for(Plant plant : plants){
             Vector2d plantPosition = plant.getPosition();
             if (this.map.isOccupiedByAnimal(plantPosition)){
                 List<Animal> eatingAnimals = this.map.animalsAt(plantPosition);

                 if (eatingAnimals != null){
                    Animal eatingAnimal = this.map.priority(eatingAnimals);
                    eatingAnimal.addEnergy(5); //wczytywane z pliku na początku
                    plants.remove(plant);
                 }
            }
        }
        //rozmnażanie się najedzonych zwierząt znajdujących się na tym samym polu,
        List<Animal> newAnimals =  this.map.copulation();
        for(Animal animal : newAnimals){
            animals.add(animal);
        }
        //wzrastanie nowych roślin na wybranych polach mapy.
        //trzeba dokończyć(w mapie jest funkcja)

        //usunięcie martwych zwierząt z mapy,
        for (Animal animal: animals){
            if(animal.getEnergy() <= 0){
                animals.remove(animal);
                this.map.addDeadPosition(animal.position);
            }
        }

    }

//    private Animal priority(List<Animal> animalsList){
//        Collections.sort(animalsList);
//        if (animalsList.get(0).getEnergy() == animalsList.get(1).getEnergy()){
//            Collections.sort(animalsList, new Comparator<Animal>() {
//                @Override
//                public int compare(Animal animal1, Animal animal2) {
//                    return animal1.getAge() - animal2.getAge();
//                }
//            });
//            if(animalsList.get(0).getAge() == animalsList.get(1).getAge()){
//                Collections.sort(animalsList, new Comparator<Animal>() {
//                    @Override
//                    public int compare(Animal animal1, Animal animal2) {
//                        return animal1.getKids() - animal2.getKids();
//                    }
//                });
//                if(animalsList.get(0).getKids() == animalsList.get(1).getKids()){
//                    List<Animal> temporary = new ArrayList<>();
//                    for (Animal animal: animalsList){
//                        if(animal.getKids() == animalsList.get(0).getKids()){
//                            temporary.add(animal);
//                        }else {
//                            break;
//                        }
//                    }
//                    Random random = new Random();
//                    return temporary.get(random.nextInt(temporary.size()));
//                }else {
//                    return animalsList.get(0);
//                }
//            }
//            else {
//                return animalsList.get(0);
//            }
//        }else {
//            return animalsList.get(0);
//        }
//    }

}
