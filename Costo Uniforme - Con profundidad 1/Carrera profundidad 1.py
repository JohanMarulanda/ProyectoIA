#-------------------------------------------------------------------------------
# Name:        module1
# Purpose:
#
# Author:      CRSTHE1
#
# Created:     20/04/2018
# Copyright:   (c) CRSTHE1 2018
# Licence:     <your licence>
#-------------------------------------------------------------------------------
#enconding: utf-8
import tkinter as tk
import time


costos=[0,0,0]
copiaCosto = [0,0,0]

posicionTijera = 0
tijeraEspera = 0
copiaposicionTijera = 0
copiatijeraEspera = 0

posicionPiedra = 0
piedraEspera = 0
copiaposicionPiedra = 0
copiapiedraEspera = 0

posicionPapel = 0
papelEspera = 0
copiaposicionPapel = 0
copiapapelEspera = 0

costosMemoriza = [0,0,0]
costosEnRama = [0,0,0]

memoriza=[]
carrera=[]
acabo =False

tamCarrera=0

enemigos=[]

def pintaMatriz(c):
    for i in range(3):
        carrera.append([0]*c)
        memoriza.append([0]*c)
    print(carrera)

def calculaCosto(robot,enemigo1,enemigo2,enemigo3):
    global costos
    global copiaCosto
    if robot == 1:
        if enemigo1==4 and enemigo2==4 and enemigo3==4:
            costos[0]+=3
            copiaCosto[0]=3
        elif enemigo1==4 and enemigo2!=4 and enemigo3!=4:
            costos[0]+=5
            copiaCosto[0]=5
        elif enemigo1!=4 and enemigo2==4 and enemigo3!=4:
            costos[0]+=5
            copiaCosto[0]=5
        elif enemigo1!=4 and enemigo2!=4 and enemigo3==4:
            costos[0]+=5
            copiaCosto[0]=5
        elif enemigo1==4 and enemigo2==4 and enemigo3!=4:
            costos[0]+=4
            copiaCosto[0]=4
        elif enemigo1==4 and enemigo2!=4 and enemigo3==4:
            costos[0]+=4
            copiaCosto[0]=4
        elif enemigo1!=4 and enemigo2==4 and enemigo3==4:
            costos[0]+=4
            copiaCosto[0]=4
        elif enemigo1!=4 and enemigo2!=4 and enemigo3!=4:
            costos[0]+=6
            copiaCosto[0]=6
    elif robot==2:
        if enemigo1==5 and enemigo2==5 and enemigo3==5:
            costos[1]+=3
            copiaCosto[1]=3
        elif enemigo1==5 and enemigo2!=5 and enemigo3!=5:
            costos[1]+=5
            copiaCosto[1]=5
        elif enemigo1!=5 and enemigo2==5 and enemigo3!=5:
            costos[1]+=5
            copiaCosto[1]=5
        elif enemigo1!=5 and enemigo2!=5 and enemigo3==5:
            costos[1]+=5
            copiaCosto[1]=5
        elif enemigo1==5 and enemigo2==5 and enemigo3!=5:
            costos[1]+=4
            copiaCosto[1]=4
        elif enemigo1==5 and enemigo2!=5 and enemigo3==5:
            costos[1]+=4
            copiaCosto[1]=4
        elif enemigo1!=5 and enemigo2==5 and enemigo3==5:
            costos[1]+=4
            copiaCosto[1]=4
        elif enemigo1!=5 and enemigo2!=5 and enemigo3!=5:
            costos[1]+=6
            copiaCosto[1]=6
    elif robot==3:
        if enemigo1==6 and enemigo2==6 and enemigo3==6:
            costos[2]+=3
            copiaCosto[2]=3
        elif enemigo1==6 and enemigo2!=6 and enemigo3!=6:
            costos[2]+=5
            copiaCosto[2]=5
        elif enemigo1!=6 and enemigo2==6 and enemigo3!=6:
            costos[2]+=5
            copiaCosto[2]=5
        elif enemigo1!=6 and enemigo2!=6 and enemigo3==6:
            costos[2]+=5
            copiaCosto[2]=5
        elif enemigo1==6 and enemigo2==6 and enemigo3!=6:
            costos[2]+=4
            copiaCosto[2]=4
        elif enemigo1==6 and enemigo2!=6 and enemigo3==6:
            costos[2]+=4
            copiaCosto[2]=4
        elif enemigo1!=6 and enemigo2==6 and enemigo3==6:
            costos[2]+=4
            copiaCosto[2]=4
        elif enemigo1!=6 and enemigo2!=6 and enemigo3!=6:
            costos[2]+=6
            copiaCosto[2]=6

def compruebaGanador(m):
   acabo = False

   if(posicionTijera >= m):
      acabo = True
      print("LA TIJERA HA GANADO!")
   if(posicionPiedra >= m):
      acabo = True
      print( "LA PIEDRA HA GANADO!")
   if(posicionPapel >= m):
      acabo = True
      print( "EL PAPEL HA GANADO!")

   return acabo



def mostrarMatriz(matriz):
   print('\n')
   for i in range(3):
      print(matriz[i],'\n')


#interfaz

ventana=tk.Tk()
ventana.title("CARRERA")
scrollbar=tk.Scrollbar(ventana, orient=tk.HORIZONTAL)
canvas =tk.Canvas(ventana,xscrollcommand=scrollbar.set,width=1000, height=200)
scrollbar.config(command=canvas.xview)
scrollbar.pack(side=tk.BOTTOM,fill=tk.X)

#frame -> ventana principal
frame =tk.Frame(canvas)
frame.grid(column=0,row=0,padx=(50,50),pady=(20,10))
frame.rowconfigure(0,weight=1)

canvas.pack(side="bottom",fill="both",expand=True)
canvas.create_window(0,0,window=frame,anchor='nw')


cesped=tk.PhotoImage(file="cesped.png")

tijera = tk.PhotoImage(file="scissors_opt.png")
papel = tk.PhotoImage(file="paper_opt.png")
piedra = tk.PhotoImage(file="stone_opt.png")

planta = tk.PhotoImage(file="plant-512_opt.png")
ladron = tk.PhotoImage(file="thief_opt.png")
homero = tk.PhotoImage(file="homer-simpson_opt.png")


def ganador(duracion,texto):

    ventana2=tk.Tk()
    ventana2.title("RESULTADOS")
    canvas2 =tk.Canvas(ventana2,width=200, height=90)

    #frame -> ventana principal
    frame2 =tk.Frame(canvas2)
    frame2.grid(column=0,row=0,padx=(50,50),pady=(20,10))
    frame2.rowconfigure(0,weight=1)

    canvas2.pack(side="bottom",fill="both",expand=True)
    canvas2.create_window(0,0,window=frame2,anchor='nw')

    etiqueta =tk.Label(frame2,text=texto)
    etiqueta.grid(column=0,row=0)
    etiqueta =tk.Label(frame2,text="duracion: ")
    etiqueta.grid(column=0,row=1)
    etiqueta =tk.Label(frame2,text=duracion)
    etiqueta.grid(column=2,row=1)


def guiInicial(c):
    imagenAnchuraMaxima=50
    imagenAlturaMaxima=50
    for h in range(c):
        etiqueta=tk.Label(frame,text=h)
        etiqueta.grid(column=h,row=4)
        etiqueta =tk.Label(frame,image=cesped, width=imagenAnchuraMaxima, height=imagenAlturaMaxima)
        etiqueta.grid(column=h,row=0)
        etiqueta =tk.Label(frame,image=cesped, width=imagenAnchuraMaxima, height=imagenAlturaMaxima)
        etiqueta.grid(column=h,row=1)
        etiqueta =tk.Label(frame,image=cesped, width=imagenAnchuraMaxima, height=imagenAlturaMaxima)
        etiqueta.grid(column=h,row=2)
    for i in range(3):
        for j in range(len(enemigos)):
            if carrera[i][enemigos[j]]==4:
                etiqueta =tk.Label(frame,image=planta, width=imagenAnchuraMaxima, height=imagenAlturaMaxima)
                etiqueta.grid(column=enemigos[j],row=i)
            if carrera[i][enemigos[j]]==5:
                etiqueta =tk.Label(frame,image=ladron, width=imagenAnchuraMaxima, height=imagenAlturaMaxima)
                etiqueta.grid(column=enemigos[j],row=i)
            if carrera[i][enemigos[j]]==6:
                etiqueta =tk.Label(frame,image=homero, width=imagenAnchuraMaxima, height=imagenAlturaMaxima)
                etiqueta.grid(column=enemigos[j],row=i)


def actulizar(posT,posS,posP):
  imagenAnchuraMaxima=50
  imagenAlturaMaxima=50

  etiqueta =tk.Label(frame,image=tijera,width=imagenAnchuraMaxima, height=imagenAlturaMaxima)
  etiqueta.grid(column=posT,row=0)
  if posT>2:
    etiqueta =tk.Label(frame,image=cesped, width=imagenAnchuraMaxima, height=imagenAlturaMaxima)
    etiqueta.grid(column=posT-1,row=0)
    etiqueta =tk.Label(frame,image=cesped, width=imagenAnchuraMaxima, height=imagenAlturaMaxima)
    etiqueta.grid(column=posT-2,row=0)
    etiqueta =tk.Label(frame,image=cesped, width=imagenAnchuraMaxima, height=imagenAlturaMaxima)
    etiqueta.grid(column=posT-3,row=0)
  if posT==1:
    etiqueta =tk.Label(frame,image=cesped, width=imagenAnchuraMaxima, height=imagenAlturaMaxima)
    etiqueta.grid(column=posT-1,row=0)


  etiqueta =tk.Label(frame,image=papel, width=imagenAnchuraMaxima, height=imagenAlturaMaxima)
  etiqueta.grid(column=posP,row=1)
  if posP>2:
    etiqueta =tk.Label(frame,image=cesped, width=imagenAnchuraMaxima, height=imagenAlturaMaxima)
    etiqueta.grid(column=posP-1,row=1)
    etiqueta =tk.Label(frame,image=cesped, width=imagenAnchuraMaxima, height=imagenAlturaMaxima)
    etiqueta.grid(column=posP-2,row=1)
    etiqueta =tk.Label(frame,image=cesped, width=imagenAnchuraMaxima, height=imagenAlturaMaxima)
    etiqueta.grid(column=posP-3,row=1)
  if posP==1:
    etiqueta =tk.Label(frame,image=cesped, width=imagenAnchuraMaxima, height=imagenAlturaMaxima)
    etiqueta.grid(column=posP-1,row=1)


  etiqueta =tk.Label(frame,image=piedra, width=imagenAnchuraMaxima, height=imagenAlturaMaxima)
  etiqueta.grid(column=posS,row=2)
  if posS>2:
    etiqueta =tk.Label(frame,image=cesped, width=imagenAnchuraMaxima, height=imagenAlturaMaxima)
    etiqueta.grid(column=posS-1,row=2)
    etiqueta =tk.Label(frame,image=cesped, width=imagenAnchuraMaxima, height=imagenAlturaMaxima)
    etiqueta.grid(column=posS-2,row=2)
    etiqueta =tk.Label(frame,image=cesped, width=imagenAnchuraMaxima, height=imagenAlturaMaxima)
    etiqueta.grid(column=posS-3,row=2)
  if posS==1:
    etiqueta =tk.Label(frame,image=cesped, width=imagenAnchuraMaxima, height=imagenAlturaMaxima)
    etiqueta.grid(column=posS-1,row=2)

  ventana.update()

"""aqui empieza la logica del juego"""
def crearTablero():
    global tamCarrera
    global enemigos
    datos=[]

    crear=True

    archivo = open("escenario.txt")
    linea=0
    while linea != '':
        # procesar línea
        linea=archivo.readline()
        datos.append(linea)
    tamCarrera = int(datos[0])

    if int(datos[0])<3 or int(datos[0])>50:
        etiqueta =tk.Label(frame,text="POR FAVOR MODIFIQUE EL TAMAÑO DE LA CARRERA POR UN VALOR ENTRE 3 Y 50")
        etiqueta.grid(column=2,row=1)
        crear=False

    elif int(datos[1])>int(datos[0]):
        etiqueta =tk.Label(frame,text="POR FAVOR MODIFIQUE LA CANTIDAD DE OBTACULOS POR UN VALOR INFERIOR AL TAMAÑO DE LA CARRERA ")
        etiqueta.grid(column=2,row=1)
        crear=False
    else:
        posiciones=[]
        for i in range(int(datos[1])):
            posiciones.append( int(datos[i+2].split()[0]))

        posiciones.sort
        print(posiciones)
        enemigos=posiciones

        for i in range(int(datos[1])-1):
            if posiciones[i]>int(datos[0]):
                etiqueta =tk.Label(frame,text="POR FAVOR MODIFIQUE LA UBICACION DE UN ENEMIGO A UNA POSICION VALIDA")
                etiqueta.grid(column=2,row=1)
                crear=False
            if posiciones[i]==posiciones[i-1]-1:
                etiqueta =tk.Label(frame,text="POR FAVOR MODIFIQUE LA UBICACION DE UN ENEMIGO A UNA POSICION VALIDA NO PUEDEN EXISTIR DOS OBSTACULOS SEGUIDOS")
                etiqueta.grid(column=2,row=1)
                crear=False

    if crear:
        pintaMatriz(tamCarrera)

        carrera[0][posicionTijera] = 1
        carrera[1][posicionPiedra] = 2
        carrera[2][posicionPapel] = 3

        for i in range(int(datos[1])):
            enemigo= datos[i+2].split()
            carrera[0][int(enemigo[0])] = int(enemigo[1])
            carrera[1][int(enemigo[0])] = int(enemigo[2])
            carrera[2][int(enemigo[0])] = int(enemigo[3])

        #MOSTRAMOS LA MATRIZ INICIAL
        mostrarMatriz(carrera)
        guiInicial(tamCarrera)
        jugar(tamCarrera)
    else:
        pass
        ventana.update()
    archivo.close()

def jugar(n):
    global costos
    global copiaCosto

    global posicionTijera
    global tijeraEspera
    global copiaposicionTijera
    global copiatijeraEspera

    global posicionPiedra
    global piedraEspera
    global copiaposicionPiedra
    global copiapiedraEspera

    global posicionPapel
    global papelEspera
    global copiaposicionPapel
    global copiapapelEspera

    global costosMemoriza
    global costosEnRama

    global memoriza
    global carrera
    global acabo

    global estados

    duracion=0

    while(acabo == False):

        #para que se active la scrollbar
        if n>17:
            canvas.config(scrollregion=canvas.bbox("all"))
        duracion+=1
        #duracion de la unidad de tiempo
        #time.sleep(0.6)
        print("inicio")
        #estado(carrera)

        actulizar(posicionTijera,posicionPiedra,posicionPapel)
        #ventana.update()



        if(carrera[0][posicionTijera+1] == 0 and tijeraEspera == 0):
            #memoriza = carrera
            carrera[0][posicionTijera] = 0

            posicionTijera +=1
            carrera[0][posicionTijera] = 1
            #Si puede avanzar, avanza 1 y muestra la matriz

            mostrarMatriz(carrera)
            #Ahora bien, estos serian los casos para cuando en la siguiente iteración, se encuentra con otro robot que está derrotando enemigos

            if(carrera[1][posicionTijera] != 0 and piedraEspera != 0 ): #CUANDO LA TIJERA SE ENCUENTRA CON LA PIEDRA DERROTANDO UN OBSTACULO
                piedraEspera = piedraEspera-1 #DESCONTAMOS LA UNIDAD DE TIEMPO QUE SE DEMORO LA TIJERA EN LLEGAR
                piedraEspera = piedraEspera * 2
                tijeraEspera = piedraEspera

            elif(carrera[2][posicionTijera] != 0 and papelEspera != 0 ): #CUANDO LA TIJERA SE ENCUENTRA CON EL PAPEL DERROTANDO OBSCATULO
                papelEspera = papelEspera-1
                papelEspera = round(papelEspera/2)
                tijeraEspera = papelEspera
            else:
                pass #NO TIENE ENEMIGOS NI AMIGOS QUE AYUDAR/DERROTAR

        elif(tijeraEspera != 0):
                  tijeraEspera-=1


        if(carrera[1][posicionPiedra+1] == 0 and piedraEspera == 0):

            carrera[1][posicionPiedra] = 0
            posicionPiedra+=1
            carrera[1][posicionPiedra] = 2
            #Si puede avanzar, avanza 1 y muestra la matriz

            mostrarMatriz(carrera)

            #Ahora bien, estos serian los casos para cuando en la siguiente iteración, se encuentra con otro robot que está derrotando enemigos
            if(carrera[0][posicionPiedra] != 0 and tijeraEspera != 0 ): #CUANDO LA PIEDRA SE ENCUENTRA CON LA TIJERA DERROTANDO UN OBSTACULO
                tijeraEspera = tijeraEspera-1 #DESCONTAMOS LA UNIDAD DE TIEMPO QUE SE DEMORO LA PIEDRA EN LLEGAR
                tijeraEspera = round(tijeraEspera/2)
                piedraEspera = tijeraEspera

            elif(carrera[2][posicionPiedra] != 0 and papelEspera != 0 ):
                papelEspera = papelEspera-1
                papelEspera = papelEspera * 2
                piedraEspera = papelEspera
            else:
                pass

        elif(piedraEspera != 0):
                  piedraEspera-=1

        if(carrera[2][posicionPapel+1] == 0 and papelEspera == 0):
            carrera[2][posicionPapel] = 0
            posicionPapel+=1

            carrera[2][posicionPapel] = 3
            #Si puede avanzar, avanza 1 y muestra la matriz

            mostrarMatriz(carrera)
            #Ahora bien, estos serian los casos para cuando en la siguiente iteración, se encuentra con otro robot que está derrotando enemigos
            if(carrera[0][posicionPapel] != 0 and tijeraEspera != 0 ): #CUANDO EL PAPEL SE ENCUENTRA CON LA TIJERA DERROTANDO UN OBSTACULO
                tijeraEspera = tijeraEspera-1 #DESCONTAMOS LA UNIDAD DE TIEMPO QUE SE DEMORO LA PIEDRA EN LLEGAR
                tijeraEspera = tijeraEspera * 2
                papelEspera = tijeraEspera

            elif(carrera[1][posicionPiedra] != 0 and piedraEspera != 0 ): #CUANDO EL PAPEL SE ENCUENTRA CON LA PIEDRA DERROTANDO OBSTACULO
                piedraEspera = piedraEspera-1
                piedraEspera = round(piedraEspera/2)
                papelEspera = piedraEspera

            else:
                pass

        elif(papelEspera != 0):

            papelEspera-=1


        if(posicionTijera >= n-1):
            acabo = True
            print("LA TIJERA HA GANADO!")
            ganador(duracion,"LA TIJERA HA GANADO")
            break


        if(posicionPiedra >= n-1):
            acabo = True
            print("LA PIEDRA HA GANADO!")
            ganador(duracion,"LA PIEDRA HA GANADO")
            break


        if(posicionPapel >= n-1):
            acabo = True
            print("EL PAPEL HA GANADO!")
            ganador(duracion,"EL PAPEL HA GANADO")
            break


        #mostrarMatriz(carrera)

        if(carrera[0][posicionTijera+1] != 0 and carrera[1][posicionPiedra+1] != 0 and carrera[2][posicionPapel+1] != 0):
            calculaCosto(1,carrera[0][posicionTijera+1],carrera[1][posicionPiedra+1], carrera[2][posicionPapel+1])
            calculaCosto(2,carrera[0][posicionTijera+1],carrera[1][posicionPiedra+1], carrera[2][posicionPapel+1])
            calculaCosto(3,carrera[0][posicionTijera+1],carrera[1][posicionPiedra+1], carrera[2][posicionPapel+1])

            if(posicionTijera == posicionPiedra and posicionPiedra == posicionPapel):
                if( costos[0] < costos[1] and costos[0] < costos[2]):
                    memoriza = carrera
                    copiatijeraEspera = tijeraEspera
                    copiapiedraEspera = piedraEspera
                    copiapapelEspera = papelEspera
                    #El tiempo de Espera de la tijera derrotando a los Enemigos
                    tijeraEspera = copiaCosto[0]

                    #Ponemos los enemigos en 0
                    carrera[0][posicionTijera+1] = 0
                    carrera[1][posicionPiedra+1] = 0
                    carrera[2][posicionPapel+1] = 0

                    #MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                    carrera[1][posicionPiedra] = 0
                    carrera[2][posicionPapel] = 0

                    posicionPiedra = posicionPiedra + 2
                    posicionPapel = posicionPapel + 2

                    carrera[1][posicionPiedra] = 2
                    carrera[2][posicionPapel] = 3

                elif( costos[1] < costos[0] and costos[1] < costos[2]):

                    memoriza = carrera
                    copiatijeraEspera = tijeraEspera
                    copiapiedraEspera = piedraEspera
                    copiapapelEspera = papelEspera
                    #El tiempo de Espera de la tijera derrotando a los Enemigos
                    piedraEspera = copiaCosto[1]

                    #Ponemos los enemigos en 0
                    carrera[0][posicionTijera+1] = 0
                    carrera[1][posicionPiedra+1] = 0
                    carrera[2][posicionPapel+1] = 0

                    #MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                    carrera[0][posicionTijera] = 0
                    carrera[2][posicionPapel] = 0

                    posicionTijera = posicionTijera + 2
                    posicionPapel = posicionPapel + 2

                    carrera[0][posicionTijera] = 1
                    carrera[2][posicionPapel] = 3

                elif(costos[2] < costos[0] and costos[2] < costos[1]):
                    memoriza = carrera
                    copiatijeraEspera = tijeraEspera
                    copiapiedraEspera = piedraEspera
                    copiapapelEspera = papelEspera

                    #El tiempo de Espera de la tijera derrotando a los Enemigos
                    papelEspera = copiaCosto[2]

                    #Ponemos los enemigos en 0
                    carrera[0][posicionTijera+1] = 0
                    carrera[1][posicionPiedra+1] = 0
                    carrera[2][posicionPapel+1] = 0

                    #MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                    carrera[0][posicionTijera] = 0
                    carrera[1][posicionPiedra] = 0

                    posicionTijera = posicionTijera + 2
                    posicionPiedra = posicionPiedra + 2

                    carrera[0][posicionTijera] = 1
                    carrera[1][posicionPiedra] = 2

                elif(costos[0] == costos[1] and costos[0] < costos[2]):
                    #PRIMERO HACEMOS UNA COPIA DE TODO EL TABLERO ANTES DE REALIZAR UNA ACCIÓN
                    memoriza = carrera
                    copiatijeraEspera = tijeraEspera
                    copiapiedraEspera = piedraEspera
                    copiapapelEspera = papelEspera

                    #El tiempo de Espera de la tijera derrotando a los Enemigos
                    tijeraEspera = copiatijeraEspera

                    #Ponemos los enemigos en 0
                    carrera[0][posicionTijera+1] = 0
                    carrera[1][posicionPiedra+1] = 0
                    carrera[2][posicionPapel+1]= 0

                    #MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                    carrera[1][posicionPiedra] = 0
                    carrera[2][posicionPapel] = 0

                    posicionPiedra = posicionPiedra + 2
                    posicionPapel = posicionPapel + 2

                    carrera[1][posicionPiedra] = 2
                    carrera[2][posicionPapel] = 3

                elif(costos[0] == costos[1] and costos[0] == costos[2]):
                    memoriza = carrera
                    copiatijeraEspera = tijeraEspera
                    copiapiedraEspera = piedraEspera
                    copiapapelEspera = papelEspera

                    #El tiempo de Espera de la tijera derrotando a los Enemigos
                    tijeraEspera = copiaCosto[0]

                    #Ponemos los enemigos en 0
                    carrera[0][posicionTijera+1] = 0
                    carrera[1][posicionPiedra+1] = 0
                    carrera[2][posicionPapel+1] = 0

                    #MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                    carrera[1][posicionPiedra] = 0
                    carrera[2][posicionPapel] = 0

                    posicionPiedra = posicionPiedra + 2
                    posicionPapel = posicionPapel + 2

                    carrera[1][posicionPiedra] = 2
                    carrera[2][posicionPapel] = 3

        elif((posicionTijera+1 > n-1) or posicionPiedra+1 >= n or posicionPapel+1 >= n):
            print("TENEMOS UN GANADOR!!! :V")
            if(posicionTijera+1 >= n):
                print("El ganador es las TIJERAS")
                ganador(duracion,"LA TIJERA HA GANADO")
                break

            elif(posicionPiedra+1 > n-1):
                print("El ganador es la PIEDRA")
                ganador(duracion,"LA PIEDRA HA GANADO")
                break

            elif(posicionPapel+1 > n-1):
                print("El ganador es el PAPEL")
                ganador(duracion,"EL PAPEL HA GANADO")
                break

            break

        elif(carrera[0][posicionTijera+1] == 0 and carrera[1][posicionPiedra+1] != 0 and carrera[2][posicionPapel+1] != 0):
            calculaCosto(3, carrera[0][posicionPiedra+1], carrera[1][posicionPiedra+1], carrera[2][posicionPapel+1])
            calculaCosto(2, carrera[0][posicionPiedra+1], carrera[1][posicionPiedra+1], carrera[2][posicionPapel+1])

            if(posicionPiedra == posicionPapel):
                if( costos[2] < costos[1] ):
                    memoriza = carrera
                    copiatijeraEspera = tijeraEspera
                    copiapiedraEspera = piedraEspera
                    copiapapelEspera = papelEspera

                    #El tiempo de Espera de la tijera derrotando a los Enemigos
                    papelEspera = copiaCosto[2]

                    #Ponemos los enemigos en 0
                    carrera[0][posicionPiedra+1] = 0
                    carrera[1][posicionPiedra+1] = 0
                    carrera[2][posicionPapel+1] = 0

                    #MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                    carrera[1][posicionPiedra] = 0

                    posicionPiedra = posicionPiedra + 2

                    carrera[1][posicionPiedra] = 2

                elif( costos[1] < costos[2] ):
                    memoriza = carrera
                    copiatijeraEspera = tijeraEspera
                    copiapiedraEspera = piedraEspera
                    copiapapelEspera = papelEspera

                    #El tiempo de Espera de la tijera derrotando a los Enemigos
                    piedraEspera = copiaCosto[1]

                    #Ponemos los enemigos en 0
                    carrera[0][posicionPiedra+1] = 0
                    carrera[1][posicionPiedra+1] = 0
                    carrera[2][posicionPapel+1] = 0

                    #MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                    carrera[2][posicionPapel] = 0

                    posicionPapel = posicionPapel + 2

                    carrera[2][posicionPapel] = 3

                elif( costos[2] == costos[1]):
                    memoriza = carrera
                    copiatijeraEspera = tijeraEspera
                    copiapiedraEspera = piedraEspera
                    copiapapelEspera = papelEspera

                    #El tiempo de Espera de la tijera derrotando a los Enemigos
                    papelEspera = copiaCosto[2]

                    #Ponemos los enemigos en 0
                    carrera[0][posicionPiedra+1] = 0
                    carrera[1][posicionPiedra+1] = 0
                    carrera[2][posicionPapel+1] = 0

                    #MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                    carrera[1][posicionPiedra] = 0

                    posicionPiedra = posicionPiedra + 2

                    carrera[1][posicionPiedra] = 2

        elif(carrera[0][posicionTijera+1] != 0 and carrera[1][posicionPiedra+1] != 0 and carrera[2][posicionPapel+1] == 0):
            if(posicionTijera == posicionPiedra):
                calculaCosto(1, carrera[0][posicionTijera+1], carrera[1][posicionPiedra+1], carrera[2][posicionPiedra+1])
                calculaCosto(2, carrera[0][posicionTijera+1], carrera[1][posicionPiedra+1], carrera[2][posicionPiedra+1])

                if( costos[0] < costos[1] ):
                    memoriza = carrera
                    copiatijeraEspera = tijeraEspera
                    copiapiedraEspera = piedraEspera
                    copiapapelEspera = papelEspera

                    #El tiempo de Espera de la tijera derrotando a los Enemigos
                    tijeraEspera = copiaCosto[0]

                    #Ponemos los enemigos en 0
                    carrera[0][posicionTijera+1] = 0
                    carrera[1][posicionPiedra+1] = 0
                    carrera[2][posicionPiedra+1] = 0

                    #MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                    carrera[1][posicionPiedra] = 0

                    posicionPiedra = posicionPiedra + 2

                    carrera[1][posicionPiedra] = 2

                elif( costos[1] < costos[0] ):
                    memoriza = carrera
                    copiatijeraEspera = tijeraEspera
                    copiapiedraEspera = piedraEspera
                    copiapapelEspera = papelEspera

                    #El tiempo de Espera de la tijera derrotando a los Enemigos
                    piedraEspera = copiaCosto[1]

                    #Ponemos los enemigos en 0
                    carrera[0][posicionTijera+1] = 0
                    carrera[1][posicionPiedra+1] = 0
                    carrera[2][posicionPiedra+1] = 0

                    #MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                    carrera[0][posicionTijera] = 0

                    posicionTijera = posicionTijera + 2

                    carrera[0][posicionTijera] = 1

                elif( costos[0] == costos[1] ):
                    memoriza = carrera
                    copiatijeraEspera = tijeraEspera
                    copiapiedraEspera = piedraEspera
                    copiapapelEspera = papelEspera

                    #El tiempo de Espera de la tijera derrotando a los Enemigos
                    tijeraEspera = copiaCosto[0]

                    #Ponemos los enemigos en 0
                    carrera[0][posicionTijera+1] = 0
                    carrera[1][posicionPiedra+1] = 0
                    carrera[2][posicionPiedra+1]= 0

                    #MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                    carrera[1][posicionPiedra] = 0

                    posicionPiedra = posicionPiedra + 2

                    carrera[1][posicionPiedra] = 2


        elif(carrera[0][posicionTijera+1] != 0 and carrera[1][posicionPiedra+1] == 0 and carrera[2][posicionPapel+1] != 0):
            calculaCosto(1, carrera[0][posicionTijera+1], carrera[1][posicionPapel+1], carrera[2][posicionPapel+1])
            calculaCosto(3, carrera[0][posicionTijera+1], carrera[1][posicionPapel+1], carrera[2][posicionPapel+1])

            if(posicionTijera == posicionPapel):
                if( costos[0] < costos[2]):
                    memoriza = carrera
                    copiatijeraEspera = tijeraEspera
                    copiapiedraEspera = piedraEspera
                    copiapapelEspera = papelEspera

                    tijeraEspera = copiaCosto[0]

                    #Ponemos los enemigos en 0
                    carrera[0][posicionTijera+1] = 0
                    carrera[1][posicionPapel+1] = 0
                    carrera[2][posicionPapel+1] = 0

                    #MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                    carrera[2][posicionPapel] = 0

                    posicionPapel = posicionPapel + 2

                    carrera[2][posicionPapel] = 3

                elif( costos[2] < costos[0] ):
                    memoriza = carrera
                    copiatijeraEspera = tijeraEspera
                    copiapiedraEspera = piedraEspera
                    copiapapelEspera = papelEspera

                    #El tiempo de Espera de la tijera derrotando a los Enemigos
                    papelEspera = copiaCosto[2]

                    #Ponemos los enemigos en 0
                    carrera[0][posicionTijera+1] = 0
                    carrera[1][posicionPapel+1] = 0
                    carrera[2][posicionPapel+1] = 0

                    #MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                    carrera[0][posicionTijera] = 0

                    posicionTijera = posicionTijera + 2

                    carrera[0][posicionTijera] = 1

                elif( costos[2] == costos[0] ):
                    memoriza = carrera
                    copiatijeraEspera = tijeraEspera
                    copiapiedraEspera = piedraEspera
                    copiapapelEspera = papelEspera

                    tijeraEspera = copiaCosto[0]

                    #Ponemos los enemigos en 0
                    carrera[0][posicionTijera+1] = 0
                    carrera[1][posicionPapel+1] = 0
                    carrera[2][posicionPapel+1] = 0

                    #MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                    carrera[2][posicionPapel] = 0

                    posicionPapel = posicionPapel + 2

                    carrera[2][posicionPapel] = 3

        #LAS SIGUIENTES CONDICIONES SON PARA CUANDO SOLO HAY UN ROBOT Y LLEGA A UNOS OBSTACULOS
        elif(carrera[0][posicionTijera+1] != 0 and carrera[1][posicionTijera] == 0 and carrera[2][posicionTijera] == 0):
            calculaCosto(1, carrera[0][posicionTijera+1], carrera[1][posicionTijera+1], carrera[2][posicionTijera+1])

            tijeraEspera = copiaCosto[0]

            #Ponemos los enemigos en 0
            carrera[0][posicionTijera+1] = 0
            carrera[1][posicionTijera+1] = 0
            carrera[2][posicionTijera+1] = 0

        elif(carrera[1][posicionPiedra+1] != 0 and carrera[0][posicionPiedra] == 0 and carrera[2][posicionPiedra] == 0):
            calculaCosto(2, carrera[0][posicionPiedra+1], carrera[1][posicionPiedra+1], carrera[2][posicionPiedra+1])

            piedraEspera = copiaCosto[1]

            #Ponemos los enemigos en 0
            carrera[0][posicionPiedra+1] = 0
            carrera[1][posicionPiedra+1] = 0
            carrera[2][posicionPiedra+1] = 0

        elif(carrera[2][posicionPapel+1] != 0 and carrera[0][posicionPapel] == 0 and carrera[1][posicionPapel] == 0):
            calculaCosto(3, carrera[0][posicionPapel+1], carrera[1][posicionPapel+1], carrera[2][posicionPapel+1])

            papelEspera = copiaCosto[2]

            #Ponemos los enemigos en 0
            carrera[0][posicionPapel+1] = 0
            carrera[1][posicionPapel+1] = 0
            carrera[2][posicionPapel+1] = 0

        if(posicionTijera >= n-1):
            acabo = True
            ganador(duracion,"LA TIJERA HA GANADO")
            break


        if(posicionPiedra >= n-1):
            acabo = True
            print("LA PIEDRA HA GANADO!")
            ganador(duracion,"LA PIEDRA HA GANADO")
            break


        if(posicionPapel >= n-1):
            acabo = True
            print("EL PAPEL HA GANADO!")
            ganador(duracion,"EL PAPEL HA GANADO")
            break

        mostrarMatriz(carrera)
        actulizar(posicionTijera,posicionPiedra,posicionPapel)


def main():

    crearTablero()
    actulizar(posicionTijera,posicionPiedra,posicionPapel)
    ventana.update()
    canvas.config(scrollregion=canvas.bbox("all"))
    ventana.mainloop()


if __name__ == '__main__':
    main()

