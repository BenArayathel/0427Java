import { Eukaryote } from "./eukaryote";
import { Taxonomy } from './taxonomy';

export class Animal implements Eukaryote {
    commonName:string;
    otherNames:string[];
    taxonomy = new Taxonomy;
    sciName:string;
    image:string;

    hasFur:boolean;
    hasScales:boolean;
    hasFeathers:boolean;
    hasLungs:boolean;
    hasGills:boolean;

    constructor(animalAttr:object, taxonomy:object) {
        for (const attr in animalAttr) {
            this[attr] = animalAttr[attr];
        }
        this.taxonomy.set("kingdom", taxonomy["kingdom"]);
        this.taxonomy.set("phylum", taxonomy["phylum"]);
        this.taxonomy.set("class", taxonomy["class"]);
        this.taxonomy.set("order", taxonomy["order"]);
        this.taxonomy.set("family", taxonomy["family"]);
        this.taxonomy.set("genus", taxonomy["genus"]);
        this.taxonomy.set("speciesEpithet", taxonomy["speciesEpithet"]);
        this.sciName = this.taxonomy.get("genus")+" "+this.taxonomy.get("species");

    }
}