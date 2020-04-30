package DetectiveComics;

public interface Abilities {

 default boolean canFly() {
  return false;
 }
 default boolean impenetrableSkin() {
  return false;
 }
 default boolean superStrength() {
  return false;
 }
 default boolean isMutant() {
  return false;
 }
 default boolean isGenius() {
  return false;
 }
 default boolean masterFighter() {
  return false;
 }
 
 String superStrength = "Super Strength";
 String flight = "Flight";
 String telekinesis = "Telekinesis";
 String heatVision = "Heat Vision";
 
 
 
}