import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class IAService {

    private apiKey = "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";
    private url = "https://openrouter.ai/api/v1/chat/completions";

    constructor() { }

    async generarRecomendacion(prompt: string): Promise<string> {
        try {
            const res = await fetch(this.url, {
                method: "POST",
                headers: {
                    "Authorization": `Bearer ${this.apiKey}`,
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    model: "openchat/openchat",
                    messages: [
                        { role: "user", content: prompt }
                    ]
                })
            });

            const data = await res.json();
            return data.choices?.[0]?.message?.content || "No se pudo generar respuesta";
        } catch (e) {
            console.error("Error IA:", e);
            return "Error generando la recomendación.";
        }
    }
}
