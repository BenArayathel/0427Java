import { Taxonomy } from './taxonomy';

export interface Eukaryote {
    commonName:string;
    otherNames:string[];
    taxonomy:Taxonomy;
    image:string;
}